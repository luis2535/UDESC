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

            System.out.println("Digite seu nome de usuario:");
            String message = scanner.nextLine();
            out.println(message);


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
                    System.out.println("Erro ao ler mensagens do servidor.");
                    e.printStackTrace();
                }
            }).start();

            while (!message.contains("/sair")) {
                System.out.println("Lista de comandos:");
                System.out.println("1 - Enviar mensagem: /send message <usuario> <mensagem>");
                System.out.println("2 - Enviar arquivo: /send file <usuario> <caminho>");
                System.out.println("3 - Listar usuários conectados: /users");
                System.out.println("4 - Desconectar: /sair");
                message = scanner.nextLine();

                out.println(message);
            }

            // Quando o cliente digita /sair, o socket será fechado de forma limpa
            System.out.println("Desconectando...");
            socket.close(); // Fecha o socket de forma controlada

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}