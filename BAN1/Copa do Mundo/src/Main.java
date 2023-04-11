/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        int op = 0;
        do{
            op = menu();
            try {
                switch (op) {
                    case 1: new IngressoController().createIngresso(con);
                            break;
                    case 2: new TorcedorController().createTorcedor(con);
                            break;
                    case 3: new IngressoController().listarIngresso(con);
                            break;
                    case 4: new TorcedorController().listarTorcedor(con);
                            break;
                    case 5: new TorcedorController().listarTorcedorIngresso(con);
                            break;
                    case 6: new IngressoController().listarIngressoPreco(con);
                }
            }catch(SQLException ex) {
                //ex.printStackTrace();
                System.err.println(ex.getMessage());
                continue;
            }
        } while(op>0 && op<7);  
        con.close();
    }    
    
    private static int menu() {
        System.out.println("");
        System.out.println("Informe o número da opção que desejas executar: ");
        System.out.println("1 - Inserir um novo ingresso");
        System.out.println("2 - Inserir um novo torcedor");
        System.out.println("3 - Exibir todos os ingressos");
        System.out.println("4 - Exibir todos os torcedores");
        System.out.println("5 - Exibir todos os torcedores e seus respectivos ingressos");
        System.out.println("6 - Exibir os ingressos cuja preço é maior que a media de preços");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.print("Sua opção: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}
