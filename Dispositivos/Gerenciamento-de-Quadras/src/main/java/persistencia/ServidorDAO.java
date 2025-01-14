package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Servidor;
import dados.Usuario;
import excecoes.DeleteException;
import excecoes.InsertException;
import excecoes.SelectException;
import excecoes.UpdateException;

public class ServidorDAO {
	private static ServidorDAO instance = null;
    private static UsuarioDAO usuarioDAO = null;
    
    private PreparedStatement selectNewId;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement selectAll;

    public static ServidorDAO getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new ServidorDAO();
        }
        return instance;
    }
    
    private ServidorDAO() throws ClassNotFoundException, SQLException {
        Connection conexao = Conexao.getConexao();
        
        selectNewId = conexao.prepareStatement("SELECT nextval('servidor_id_seq');");
        insert = conexao.prepareStatement("INSERT INTO Servidor (cpf, pnome, unome, email, senha, status, id_servidor, funcao) VALUES (?,?,?,?,?,?,?,?);");
        delete = conexao.prepareStatement("DELETE FROM Servidor WHERE cpf = ?;");
        update = conexao.prepareStatement(
                "UPDATE Servidor SET pnome = ?, unome = ?, email = ?, senha = ?, status = ?, id_servidor = ?, funcao = ? WHERE cpf = ?;");
        select = conexao.prepareStatement("SELECT * FROM Servidor WHERE cpf = ?;");
        selectAll = conexao.prepareStatement("SELECT * FROM Servidor;");
    
        usuarioDAO = UsuarioDAO.getInstance();
    }
    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SelectException("novo id da tabela Servidor");
        }
        return 0;
    }



    public void insert(Servidor servidor) throws SelectException, InsertException, UpdateException {
        try {
            // Verifica se o usuário já existe
            Usuario usuarioExistente = usuarioDAO.select(servidor.getCpf());
            if (usuarioExistente == null) {
                // Se o usuário não existir, insere o novo usuário
                usuarioDAO.insert(servidor);
            } else {
                // Se o usuário já existir, atualiza os dados do usuário existente
                usuarioDAO.update(servidor);
            }

            // Insere os dados específicos do bolsista
            insert.setString(1, servidor.getCpf());
            insert.setString(2, servidor.getPnome());
            insert.setString(3, servidor.getUnome());
            insert.setString(4, servidor.getEmail());
            insert.setString(5, servidor.getSenha());
            insert.setString(6, servidor.getStatus());
            insert.setInt(7, selectNewId());
            insert.setString(8, servidor.getFuncao());
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new InsertException("servidor");
        }
    }
    public void delete(Servidor servidor) throws DeleteException {
    	try {
    		delete.setString(1, servidor.getCpf());
    		delete.executeUpdate();
    	}catch(SQLException e) {
    		throw new DeleteException("servidor");
    	}
    }
    public void update(Servidor servidor) throws UpdateException{
    	try {
    		update.setString(1, servidor.getPnome());
        	update.setString(2, servidor.getUnome());
        	update.setString(3, servidor.getEmail());
        	update.setString(4, servidor.getSenha());
        	update.setString(5, servidor.getStatus());
        	update.setInt(6, servidor.getId_servidor());
        	update.setString(7, servidor.getFuncao());
        	update.setString(8, servidor.getCpf());
        	update.executeUpdate();
    	} catch(SQLException e) {
    		throw new UpdateException("servidor");
    	}
    }
    public Servidor select(String cpf) throws SelectException{
    	try {
            select.setString(1, cpf);
            ResultSet rs = select.executeQuery();

            if(rs.next()){
                String pnome = rs.getString(2);
                String unome = rs.getString(3);
                String email = rs.getString(4);
                String senha = rs.getString(5);
                String status = rs.getString(6);
                int id_servidor = rs.getInt(7);
                String funcao = rs.getString(8);
                
                

                return new Servidor(cpf, pnome, unome, email, senha, status, id_servidor, funcao);
            }

        } catch (SQLException e) {
            throw new SelectException("servidor");
        }

        return null;

    }
    
    public List<Servidor> selectAll() throws SelectException {

        List<Servidor> lista = new LinkedList<>();

        try {
            ResultSet rs = selectAll.executeQuery();

            while(rs.next()){
                String cpf = rs.getString(1);
                String pnome = rs.getString(2);
                String unome = rs.getString(3);
                String email = rs.getString(4);
                String senha = rs.getString(5);
                String status = rs.getString(6);
                int id_servidor = rs.getInt(7);
                String funcao = rs.getString(8);
                
                

                lista.add(new Servidor(cpf, pnome, unome, email, senha, status, id_servidor, funcao));
            }
        } catch (SQLException e) {
            throw new SelectException("servidor");
        }

        return lista;
    }

}
