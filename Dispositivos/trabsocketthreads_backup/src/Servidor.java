import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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

    private static void handleClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Recebe o nome do usuário na primeira mensagem
            String nomeUsuario = in.readLine();
            System.out.println("Usuário conectado: " + nomeUsuario);

            ClienteConectadoPayload clienteConectadoPayload = new ClienteConectadoPayload(clientSocket, nomeUsuario);
            clientes.add(clienteConectadoPayload);

            String message;
            while ((message = in.readLine()) != null && !message.contains("/sair")) {
                if (message.startsWith("/send file")) {
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
            }

            // Remove o cliente da lista quando ele sair
            clientes.removeIf(cliente -> cliente.getIp().equals(clientSocket));
            clientSocket.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}