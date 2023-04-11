
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class IngressoController {
    
    public void createIngresso(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para a criar um novo Ingresso: ");
        System.out.print("Código do ingresso: ");
        int cod_i = input.nextInt();
        System.out.print("Assento: ");
        int assento = input.nextInt();
        System.out.print("Preço: ");
        float preco = input.nextFloat();
        System.out.print("Número ingresso: ");
        int nro_i = input.nextInt();
        Ingresso ab = new Ingresso(cod_i, assento, preco, nro_i);
        
        IngressoModel.create(ab, con);
        System.out.println("Ingresso criado com sucesso!!");
    }

    void listarIngresso(Connection con) throws SQLException {
        HashSet all = IngressoModel.listAll(con);
        Iterator<Ingresso> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
    void listarIngressoPreco(Connection con) throws SQLException{
    	HashSet all = IngressoModel.listPreco(con);
    	Iterator<Ingresso> it = all.iterator();
    	while(it.hasNext()) {
    		System.out.println(it.next().toString());
    	}
    }
}
