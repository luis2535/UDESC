import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;


public class Servidor {
    public static void main(String args[]) throws IOException{
        var servidor = new ServerSocket(12345);
        System.out.println("Servidor iniciado na 12345!");

        var cliente = servidor.accept();
        System.out.println("Cliente " + cliente.getInetAddress().getHostAddress());
        var saida = new Scanner(cliente.getInputStream());
        while (saida.hasNextLine()) {
            System.out.println(saida.nextLine());
        }
        saida.close();
        servidor.close();
        cliente.close();

    }

}
