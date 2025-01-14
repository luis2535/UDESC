package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.*;
import excecoes.*;

public class UsuarioDAO {
	private static UsuarioDAO instance = null;
	
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement selectAll;
    
    public static UsuarioDAO getInstance() throws ClassNotFoundException, SQLException {

        if(instance == null){
            instance = new UsuarioDAO();
        }
        return instance;
    }
    
    private UsuarioDAO() throws ClassNotFoundException, SQLException {
        Connection conexao = Conexao.getConexao();

        insert = conexao.prepareStatement("insert into Usuario values (?,?,?,?,?,?);");
        delete = conexao.prepareStatement("delete from Usuario where cpf = ?;");
        update = conexao.prepareStatement("update Usuario set pnome = ?, unome = ?, email = ?, senha = ?, status = ? where cpf = ?;");
        select = conexao.prepareStatement("select * from Usuario where cpf = ?;");
        selectAll = conexao.prepareStatement("select * from Usuario;");
    }

    public void insert(Usuario usuario) throws SelectException, InsertException {

        try {
            insert.setString(1, usuario.getCpf());
            insert.setString(2, usuario.getPnome());
            insert.setString(3, usuario.getUnome());
            insert.setString(4, usuario.getEmail());
            insert.setString(5, usuario.getSenha());
            insert.setString(6, usuario.getStatus());
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new InsertException("Usuario");
        }

    }
    public void delete(Usuario usuario) throws DeleteException {

        try {
            delete.setString(1, usuario.getCpf());
            delete.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteException("Usuario");
        }
    }
    
    public void update(Usuario usuario) throws UpdateException {

        try {
        	update.setString(1, usuario.getPnome());
        	update.setString(2, usuario.getUnome());
        	update.setString(3, usuario.getEmail());
        	update.setString(4, usuario.getSenha());
        	update.setString(5, usuario.getStatus());
        	update.setString(6, usuario.getCpf());
            update.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateException("Usuario");
        }

    }
    
    public Usuario select(String cpf) throws SelectException {

        try {
            select.setString(1, cpf);
            ResultSet rs = select.executeQuery();

            if(rs.next()){
                String pnome = rs.getString(2);
                String unome = rs.getString(3);
                String email = rs.getString(4);
                String senha = rs.getString(5);
                String status = rs.getString(6);
                
                

                return new Usuario(cpf, pnome, unome, email, senha, status);
            }

        } catch (SQLException e) {
            throw new SelectException("Usuario");
        }

        return null;

    }
    
    public List<Usuario> selectAll() throws SelectException {

        List<Usuario> lista = new LinkedList<>();

        try {
            ResultSet rs = selectAll.executeQuery();

            while(rs.next()){
                String cpf = rs.getString(1);
                String pnome = rs.getString(2);
                String unome = rs.getString(3);
                String email = rs.getString(4);
                String senha = rs.getString(5);
                String status = rs.getString(6);
                

                lista.add(new Usuario(cpf, pnome, unome, email, senha, status));
            }
        } catch (SQLException e) {
            throw new SelectException("Usuario");
        }

        return lista;
    }
    
}
