import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Servidor extends Thread {

    public static List<ClienteConectadoPayload> clientes = new ArrayList<>();

    public static void main(String... args) {
        int port = 1234;
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logArquivo(String clientAddress, String nomeUsuario) {
        String nomeArquivo = "log.txt"; // Nome do arquivo de log
        LocalDateTime agora = LocalDateTime.now(); // Pega o horário atual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Formato do horário
        String horarioConexao = agora.format(formatter); // Formata o horário

        try {
            FileWriter fileWriter = new FileWriter(nomeArquivo, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Cliente conectado - IP: " + clientAddress + ", Nome: " + nomeUsuario + ", Horário: " + horarioConexao);

            printWriter.close();
            fileWriter.close();

            System.out.println("Log de conexão adicionado ao arquivo.");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de log.");
            e.printStackTrace();
        }
    }


    private static void handleClient(Socket clientSocket) {
        try {
            String clientAddress = clientSocket.getInetAddress().getHostAddress();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String nomeUsuario = null;
            boolean nomeJaEmUso;

            do {
                out.println("Digite seu nome de usuário:");
                nomeUsuario = in.readLine();

                String finalNomeUsuario = nomeUsuario;
                nomeJaEmUso = clientes.stream().anyMatch(cliente -> cliente.getNome().equals(finalNomeUsuario));
                if (nomeJaEmUso) {
                    out.println("Erro: Nome de usuário '" + nomeUsuario + "' já está em uso. Tente outro nome.");
                }
            } while (nomeJaEmUso);

            System.out.println("Usuário conectado: " + nomeUsuario + " (" + clientAddress + ")");

            // Adiciona o cliente à lista de conectados
            ClienteConectadoPayload clienteConectadoPayload = new ClienteConectadoPayload(clientSocket, nomeUsuario);
            clientes.add(clienteConectadoPayload);

            // Loga a conexão no arquivo
            logArquivo(clientAddress + ":" + String.valueOf(clientSocket.getPort()), nomeUsuario);

            String message;
            while ((message = in.readLine()) != null && !message.contains("/sair")) {
                if (message.contains("/send message")) {
                    Pattern pattern = Pattern.compile("/send message <(\\S+)> <(.*)>");
                    Matcher matcher = pattern.matcher(message);

                    if (matcher.find()) {
                        String destinatario = matcher.group(1); // nome do destinatário
                        String conteudoMensagem = matcher.group(2); // conteúdo da mensagem

                        // Encontra o cliente alvo
                        Optional<Socket> targetSocket = clientes.stream()
                                .filter(cliente -> cliente.getNome().equals(destinatario)) // compara o nome do destinatário
                                .map(ClienteConectadoPayload::getIp)
                                .findFirst();

                        if (targetSocket.isPresent()) {
                            try {
                                PrintWriter targetOut = new PrintWriter(targetSocket.get().getOutputStream(), true);
                                targetOut.println(nomeUsuario + ": " + conteudoMensagem); // envia a mensagem para o destinatário
                            } catch (IOException e) {
                                System.out.println("Erro ao enviar mensagem para o cliente.");
                                e.printStackTrace();
                            }
                        } else {
                            out.println("Usuário " + destinatario + " não encontrado.");
                        }
                    }

                }
                if (message.contains("/send file")) {
                    Pattern pattern = Pattern.compile("/send file <(\\S+)> <(.*)>");
                    Matcher matcher = pattern.matcher(message);

                    if (matcher.find()) {
                        String destinatario = matcher.group(1); // Nome do destinatário
                        String caminhoArquivo = matcher.group(2); // Caminho do arquivo

                        Optional<Socket> targetSocket = clientes.stream()
                                .filter(cliente -> cliente.getNome().equals(destinatario))
                                .map(ClienteConectadoPayload::getIp)
                                .findFirst();

                        if (targetSocket.isPresent()) {
                            try {
                                File arquivo = new File(caminhoArquivo);
                                if (!arquivo.exists()) {
                                    out.println("Arquivo não encontrado.");
                                    continue;
                                }

                                // Log no servidor: quem está enviando para quem
                                System.out.println("Enviando arquivo '" + arquivo.getName() + "' de " + nomeUsuario + " para " + destinatario);

                                // Envia nome do arquivo para o destinatário
                                PrintWriter targetOut = new PrintWriter(targetSocket.get().getOutputStream(), true);
                                targetOut.println("INICIO_ARQUIVO:" + arquivo.getName());

                                // Envia os bytes do arquivo
                                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(arquivo));
                                OutputStream os = targetSocket.get().getOutputStream();
                                byte[] buffer = new byte[1024];
                                int bytesLidos;
                                while ((bytesLidos = bis.read(buffer)) > 0) {
                                    os.write(buffer, 0, bytesLidos);
                                }
                                bis.close();
                                os.flush();

                                // Notifica o remetente de sucesso no envio
                                out.println("Arquivo enviado para " + destinatario + " com sucesso.");
                            } catch (IOException e) {
                                System.out.println("Erro ao enviar arquivo para o cliente.");
                                e.printStackTrace();
                            }
                        } else {
                            out.println("Usuário " + destinatario + " não encontrado.");
                        }
                    }
                }
                if(message.contains("/users")){
                    PrintWriter targetOut = new PrintWriter(clientSocket.getOutputStream(), true);
                    targetOut.println("Lista de usuários conectados");
                    for(ClienteConectadoPayload  c: clientes){
                        System.out.println(c.getNome());
                        targetOut.println(c.getNome());
                    }
                }
                if (message.contains("/sair")) {
                    out.println("Desconectando...");
                    System.out.println("Cliente " + nomeUsuario + " desconectado.");
                    break;
                }

            }

            // Remover o cliente da lista quando ele se desconectar
            clientes.removeIf(cliente -> cliente.getIp().equals(clientSocket));
            clientSocket.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}