import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TorcedorModel {
	static void create(Torcedor m, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO Torcedores (cod_torcedor, nome, idade, nacionalidade, cod_ingresso) "
                    + "VALUES (?,?,?,?,?)");
            st.setInt(1, m.getCod_torcedor());
            st.setString(2, m.getNome());
            st.setInt(3, m.getIdade());
            st.setString(4, m.getNacionalidade());
            st.setInt(5, m.getCod_ingresso());
            st.execute();
            st.close();
    }
    
    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT cod_torcedor, nome, idade, nacionalidade, cod_ingresso FROM Torcedores";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new Torcedor(result.getInt(1), result.getString(2), result.getInt(3), 
                result.getString(4), result.getInt(5)));
            }
        return list;
    }
    static HashSet listAllWithIngresso(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql = "SELECT cod_torcedor, nome, idade, nacionalidade, Torcedores.cod_ingresso, assento, preco, numero_ingresso FROM Torcedores NATURAL JOIN Ingressos";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            Torcedor t = new Torcedor(result.getInt(1), result.getString(2), result.getInt(3),
                    result.getString(4),result.getInt(5));
            Ingresso i= new Ingresso(result.getInt(5), result.getInt(6), result.getFloat(7), result.getInt(8));
            t.setIngresso(i);
            list.add(t);
        }
        return list;
    }
}
