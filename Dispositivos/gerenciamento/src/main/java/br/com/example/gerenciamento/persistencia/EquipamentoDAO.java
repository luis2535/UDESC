package br.com.example.gerenciamento.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;

public class EquipamentoDAO {
	private static EquipamentoDAO instance = null;
	
	private PreparedStatement selectNewId;
	private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement selectAll;
    
    public static EquipamentoDAO getInstance() throws ClassNotFoundException, SQLException{
    	if(instance == null) {
    		instance = new EquipamentoDAO();
    	}
    	return instance;
    }
    
    private EquipamentoDAO() throws ClassNotFoundException, SQLException{
    	Connection conexao = Conexao.getConexao();
    	
    	selectNewId = conexao.prepareStatement("SELECT nextval('equipamento_id_seq');");
    	insert = conexao.prepareStatement("INSERT INTO Equipamento (id_equipamento, tipo, descricao) VALUES (?, ?, ?);");
    	delete = conexao.prepareStatement("DELETE FROM Equipamento where id_equipamento = ?;");
    	update = conexao.prepareStatement("UPDATE Equipamento SET tipo = ?, descricao = ? WHERE id_equipamento = ?;");
    	select = conexao.prepareStatement("SELECT * FROM Equipamento WHERE id_equipamento = ?;");
    	selectAll = conexao.prepareStatement("SELECT * FROM Equipamento;");
  
    }
    
    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SelectException("novo id da tabela equipamento");
        }
        return 0;
    }
    
    public void insert(Equipamento equipamento) throws SelectException, InsertException{
    	try {
    		insert.setInt(1, selectNewId());
    		insert.setString(2, equipamento.getTipo());
    		insert.setString(3, equipamento.getDescricao());
    		insert.executeUpdate();
    	}catch (SQLException e) {
    		throw new InsertException("Equipamento");
    	}
    }
    
    public void delete(Equipamento equipamento) throws DeleteException{
    	try {
    		delete.setInt(1, equipamento.getId_equipamento());
    		delete.executeUpdate();
    	}catch (SQLException e) {
    		throw new DeleteException("Equipamento");
    	}
    }
    
    public void update(Equipamento equipamento) throws UpdateException{
    	try {
    		update.setString(1, equipamento.getTipo());
    		update.setString(2, equipamento.getDescricao());
    		update.setInt(3, equipamento.getId_equipamento());
    		update.executeUpdate();
    	}catch(SQLException e) {
    		throw new UpdateException("Equipamento");
    	}
    }
    
    public Equipamento select(int id_equipamento) throws SelectException{
    	try {
    		select.setInt(1, id_equipamento);
    		ResultSet rs = select.executeQuery();
    		
    		if(rs.next()) {
    			String tipo = rs.getString(2);
    			String descricao = rs.getString(3);
    			
    			return new Equipamento(id_equipamento, tipo, descricao);
    		}
    	}catch(SQLException e) {
    		throw new SelectException("Equipamento");
		}
    	return null;
    }
    
    public List<Equipamento> selectAll() throws SelectException{
    	List<Equipamento> lista = new LinkedList<>();
    	
    	try {
    		ResultSet rs = selectAll.executeQuery();
    		while(rs.next()) {
    			int id_equipamento = rs.getInt(1);
    			String tipo = rs.getString(2);
    			String descricao = rs.getString(3);
    			
    			lista.add(new Equipamento(id_equipamento, tipo, descricao));
    		}
    	}catch(SQLException e) {
    		throw new SelectException("Equipamento");
    	}
    	return lista;
    }
	

}
