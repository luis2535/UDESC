import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 1234;

        try (Socket socket = new Socket(serverAddress, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            InputStream is = socket.getInputStream();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite seu nome de usuário:");
            String nomeUsuario = scanner.nextLine();
            out.println(nomeUsuario);

            new Thread(() -> {
                try {
                    String serverMessage;
                    while (true) {
                        serverMessage = in.readLine();
                        if (serverMessage != null && serverMessage.startsWith("INICIO_ARQUIVO:")) {
                            String nomeArquivo = serverMessage.split(":")[1];
                            System.out.println("Recebendo arquivo: " + nomeArquivo);

                            File arquivoRecebido = new File(nomeArquivo);
                            FileOutputStream fos = new FileOutputStream(arquivoRecebido);
                            BufferedOutputStream bos = new BufferedOutputStream(fos);

                            byte[] buffer = new byte[1024];
                            int bytesLidos;

                            // Ler bytes do arquivo e salvar no sistema
                            while ((bytesLidos = is.read(buffer)) > 0) {
                                bos.write(buffer, 0, bytesLidos);
                                if (is.available() == 0) {
                                    break;
                                }
                            }
                            bos.close();
                            fos.close();

                            System.out.println("Arquivo " + nomeArquivo + " recebido com sucesso.");
                        } else if (serverMessage != null) {
                            // Imprime mensagens normais do servidor
                            System.out.println(serverMessage);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao receber o arquivo.");
                    e.printStackTrace();
                }
            }).start();

            // Loop de envio de mensagens e arquivos
            while (true) {
                System.out.println("Digite a mensagem no formato: /send file <usuario> <caminho do arquivo>");
                String message = scanner.nextLine();

                // Enviar comando de envio de arquivo
                if (message.startsWith("/send file")) {
                    out.println(message);
                } else {
                    out.println(message);  // Enviar mensagens normais
                }

                // Se o usuário digitar '/sair', encerramos a conexão
                if (message.contains("/sair")) {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}