package br.com.example.gerenciamento.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;

public class BlocoDAO {
	private static BlocoDAO instance = null;
	
	private PreparedStatement selectNewId;
	private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement selectAll;
    
    public static BlocoDAO getInstance() throws ClassNotFoundException, SQLException {
    	if(instance == null) {
    		instance = new BlocoDAO();
    	}
    	return instance;
    };
    
    private BlocoDAO() throws ClassNotFoundException, SQLException{
    	Connection conexao = Conexao.getConexao();
    	
    	selectNewId = conexao.prepareStatement("SELECT nextval('bloco_id_seq');");
    	insert = conexao.prepareStatement("INSERT INTO Bloco (id_bloco, nome, descricao) VALUES (?, ?, ?);");
    	delete = conexao.prepareStatement("DELETE FROM Bloco where id_bloco = ?;");
    	update = conexao.prepareStatement("UPDATE Bloco SET nome = ?, descricao = ? WHERE id_bloco = ?;");
    	select = conexao.prepareStatement("SELECT * FROM Bloco WHERE id_bloco = ?;");
    	selectAll = conexao.prepareStatement("SELECT * FROM Bloco;");
    }
    
    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SelectException("novo id da tabela bloco");
        }
        return 0;
    }
    public void insert(Bloco bloco) throws SelectException, InsertException {
        try {
            insert.setInt(1, selectNewId());
            insert.setString(2, bloco.getNome());
            insert.setString(3, bloco.getDescricao()); 
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new InsertException("Bloco");
        }
    }

    public void delete(Bloco bloco) throws DeleteException{
    	try {
    		delete.setInt(1, bloco.getId_bloco());
    		delete.executeUpdate();
    	} catch (SQLException e) {
    		throw new DeleteException("Bloco");
    	}
    }
    public void update(Bloco bloco) throws UpdateException{
    	try {
    		update.setString(1, bloco.getNome());
    		update.setString(2, bloco.getDescricao());
    		update.setInt(3, bloco.getId_bloco());
    		update.executeUpdate();
    	}catch(SQLException e) {
    		throw new UpdateException("Bloco");
    	}
    }
    public Bloco select(int id_bloco) throws SelectException{
    	try {
    		select.setInt(1, id_bloco);
    		ResultSet rs = select.executeQuery();
    		
    		if(rs.next()) {
    			String nome = rs.getString(2);
    			String descricao = rs.getString(3);
    			
    			return new Bloco(id_bloco, nome, descricao);
    		}
    	}catch (SQLException e) {
    		throw new SelectException("Bloco");
    	}
    	return null;
    }
    public List<Bloco> selectAll() throws SelectException{
    	List<Bloco> lista = new LinkedList<>();
    	
    	try {
    		ResultSet rs = selectAll.executeQuery();
    		
    		while(rs.next()) {
    			int id_bloco = rs.getInt(1);
    			String nome = rs.getString(2);
    			String descricao = rs.getString(3);
    			
    			lista.add(new Bloco(id_bloco, nome, descricao));
    		}
    	}catch (SQLException e) {
    		throw new SelectException("Bloco");
    	}
    	return lista;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
