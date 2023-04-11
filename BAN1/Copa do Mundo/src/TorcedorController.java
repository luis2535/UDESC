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

public class TorcedorController {
	 public void createTorcedor(Connection con) throws SQLException {
	        Scanner input = new Scanner(System.in);
	        System.out.println("Insira os seguintes dados para a criar um novo Torcedor: ");
	        System.out.print("cod_torcedor: ");
	        int cod_torcedor = input.nextInt();
	        System.out.print("nome: ");
	        String nome = input.next();
	        System.out.print("idade: ");
	        int idade = input.nextInt();
	        System.out.print("nacionalidade: ");
	        String nacionalidade = input.next();
	        System.out.print("cod_ingresso: ");
	        int cod_ingresso = input.nextInt();
	        
	        Torcedor torcedor = new Torcedor(cod_torcedor, nome, idade, nacionalidade, cod_ingresso);
	        TorcedorModel.create(torcedor, con);
	        System.out.println("Torcedor criado com sucesso!!");
	    }

	    void listarTorcedor(Connection con) throws SQLException {
	        HashSet all = TorcedorModel.listAll(con);
	        Iterator<Torcedor> it = all.iterator();
	        while(it.hasNext()) {
	            System.out.println(it.next().toString());
	        }
	    }
	    void listarTorcedorIngresso(Connection con) throws SQLException{
	    	HashSet all = TorcedorModel.listAllWithIngresso(con);
	    	Iterator<Torcedor> it = all.iterator();
	    	while(it.hasNext()) {
	    		System.out.println(it.next().toString());
	    	}
	    }
}
