

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class IngressoModel {

    public static void create(Ingresso a, Connection con) throws SQLException {
        PreparedStatement st;
        // int cod_i,int assent,float prec,int nro_i
            st = con.prepareStatement("INSERT INTO ingressos (cod_ingresso, assento, preco, numero_ingresso) VALUES (?,?,?,?)");
            st.setInt(1, a.getCod_ingresso());
            st.setInt(2, a.getAssento());
            st.setFloat(3, a.getPreco());
            st.setInt(4, a.getNro_ingresso());
            st.execute();
            st.close();  
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT cod_ingresso, assento ,preco, numero_ingresso FROM Ingressos";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new Ingresso(result.getInt(1), result.getInt(2), result.getFloat(3), result.getInt(4)));
            }
        return list;
    }
    static HashSet listPreco(Connection con) throws SQLException{
    	Statement st;
    	HashSet list = new HashSet();
    	st = con.createStatement();
    	String sql = "SELECT cod_ingresso, assento, preco, numero_ingresso FROM Ingressos WHERE preco > (SELECT AVG(preco) FROM Ingressos)";
    	ResultSet result = st.executeQuery(sql);
    	while(result.next()) {
    		list.add(new Ingresso(result.getInt(1), result.getInt(2), result.getFloat(3), result.getInt(4)));
    	}
    	return list;
    }
}
