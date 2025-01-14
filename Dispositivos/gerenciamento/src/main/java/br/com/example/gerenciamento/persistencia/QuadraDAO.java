package br.com.example.gerenciamento.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;

public class QuadraDAO {
	private static QuadraDAO instance = null;
	private static BlocoDAO blocoDAO = null;
	
	private PreparedStatement selectNewId;
	private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement selectAll;
    
    public static QuadraDAO getInstance() throws ClassNotFoundException, SQLException{
    	if(instance == null) {
    		instance = new QuadraDAO();
    	}
    	return instance;
    }
    
    private QuadraDAO() throws ClassNotFoundException, SQLException{
    	Connection conexao = Conexao.getConexao();
    	selectNewId = conexao.prepareStatement("select nextval('quadra_id_seq');");
    	insert = conexao.prepareStatement("insert into Quadra values(?, ?, ?, ?);");
    	delete = conexao.prepareStatement("delete from Quadra where id_quadra = ?");
    	update = conexao.prepareStatement("update Quadra set modalidade = ?, descricao = ?, id_bloco = ? where id_quadra = ?;");
    	select = conexao.prepareStatement("select * from Quadra where id_quadra = ?;");
    	selectAll = conexao.prepareStatement("select * from Quadra");
    	
    	blocoDAO = BlocoDAO.getInstance();    	
    }
    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SelectException("novo id da tabela quadra");
        }
        return 0;
    }
    public void insert(Quadra quadra) throws SelectException, InsertException{
    	try {
    		insert.setInt(1, selectNewId());
    		insert.setString(2, quadra.getModalidade());
    		insert.setString(3, quadra.getDescricao());
    		insert.setInt(4, quadra.getId_bloco().getId_bloco());
    		insert.executeUpdate();
    	}catch (SQLException e) {
    		throw new InsertException("Quadra");
    	}
    }
    public void delete(Quadra quadra) throws DeleteException{
    	try {
    		delete.setInt(1, quadra.getId_quadra());
    		delete.executeUpdate();
    	}catch (SQLException e) {
    		throw new DeleteException("Quadra");
    	}
    }
    public void update(Quadra quadra) throws UpdateException{
    	try {
    		update.setString(1, quadra.getModalidade());
    		update.setString(2, quadra.getDescricao());
    		update.setInt(3, quadra.getId_bloco().getId_bloco());
    		update.setInt(4, quadra.getId_quadra());
    		update.executeUpdate();
    		
    	}catch (SQLException e) {
    		throw new UpdateException("Quadra");
    	}
    }
    public Quadra select(int id_quadra) throws SelectException{
    	try {
    		select.setInt(1, id_quadra);
    		ResultSet rs = select.executeQuery();
    		
    		if(rs.next()) {
    			String modalidade = rs.getString(2);
    			String descricao = rs.getString(3);
    			int id_bloco = rs.getInt(4);
    			Bloco bloco = blocoDAO.select(id_bloco);
    			
    			return new Quadra(id_quadra, modalidade, descricao, bloco);
    		}
    	}catch (SQLException e) {
    		throw new SelectException("Quadra");
    	}
    	return null;
    }
    public List<Quadra> selectAll() throws SelectException{
    	List<Quadra> lista = new LinkedList<>();
    	try {
    		ResultSet rs = selectAll.executeQuery();
    		while(rs.next()) {
    			int id_quadra = rs.getInt(1);
    			String modalidade = rs.getString(2);
    			String descricao = rs.getString(3);
    			int id_bloco = rs.getInt(4);
    			Bloco bloco = blocoDAO.select(id_bloco);
    			
    			lista.add(new Quadra(id_quadra, modalidade, descricao, bloco));
    			
    		}
    	}catch (SQLException e) {
    		throw new SelectException("Quadra");
    	}
    	return lista;
    }

}
