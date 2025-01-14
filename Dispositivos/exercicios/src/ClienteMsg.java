import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMsg {
    public static void main(String[] args) throws IOException {
        var servidor = new Socket("127.0.0.1", 12345);

        // Thread para enviar mensagens ao servidor
        new Thread(() -> {
            try (OutputStream output = servidor.getOutputStream();
                 Scanner teclado = new Scanner(System.in);
                 PrintStream saida = new PrintStream(output)) {

                while (teclado.hasNextLine()) {
                    String msg = teclado.nextLine();
                    saida.println("Cliente: " + msg);
                    if(msg == "!sair"){

                        output.close();
                        teclado.close();
                        servidor.close();


                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Thread para receber mensagens do servidor
        new Thread(() -> {
            try (InputStream input = servidor.getInputStream();
                 Scanner scanner = new Scanner(input)) {

                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
