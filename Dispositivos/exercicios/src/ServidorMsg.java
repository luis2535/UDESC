import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class ServidorMsg {
    public static void main(String args[]) throws IOException{
        var servidor = new ServerSocket(12345);
        System.out.println("Servidor iniciado na 12345!");



        while(true){
            Socket cliente = servidor.accept();
            LocalDateTime connectionTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedConnectionTime = connectionTime.format(formatter);
            System.out.println("Cliente " + cliente.getInetAddress().getHostAddress() + "Horario de conexao: " + formattedConnectionTime);

            new Thread(new ClientHandlerInput(cliente)).start();

            new Thread(new ClientHandlerOutput(cliente)).start();
        }

    }

    static class ClientHandlerInput implements Runnable{
        private Socket client;

        public ClientHandlerInput(Socket socket){
            this.client = socket;
        }
        @Override
        public void run() {


            try (Scanner scanner = new Scanner(client.getInputStream())) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                    if(scanner.toString() == "!sair"){
                        client.close();
                        LocalDateTime disconnectionTime = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        String formattedDisconnectionTime = disconnectionTime.format(formatter);
                        System.out.println("Cliente " + client.getInetAddress().getHostAddress() + " - Horário de desconexão: " + formattedDisconnectionTime);

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ClientHandlerOutput implements Runnable{
        private Socket client;

        public ClientHandlerOutput(Socket socket){
            this.client = socket;
        }

        @Override
        public void run() {
            try (OutputStream output = client.getOutputStream()) {
                try (Scanner teclado = new Scanner(System.in)) {
                    try (PrintStream saida = new PrintStream(output)) {

                        while (teclado.hasNextLine()) {
                            String msg = teclado.nextLine();
                            saida.println("Servidor: " + msg);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}