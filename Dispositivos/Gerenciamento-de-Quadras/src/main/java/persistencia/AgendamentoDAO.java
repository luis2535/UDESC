package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dados.*;
import excecoes.*;

public class AgendamentoDAO {
	private static AgendamentoDAO instance = null;
	private static QuadraDAO quadraDAO = null;
	private static UsuarioDAO usuarioDAO = null;
	
	private PreparedStatement selectNewId;
	private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement selectAll;
    
    public static AgendamentoDAO getInstance() throws ClassNotFoundException, SQLException{
    	if(instance == null) {
    		instance = new AgendamentoDAO();
    	}
    	return instance;
    }
    
    private AgendamentoDAO() throws ClassNotFoundException, SQLException{
    	Connection conexao = Conexao.getConexao();
    	selectNewId = conexao.prepareStatement("select nextval('agendamento_id_seq');");
    	insert = conexao.prepareStatement("insert into Agendamento values(?, ?, ?, ?, ?, ?, ?);");
    	delete = conexao.prepareStatement("delete from Agendamento where id_agendamento = ?;");
    	update = conexao.prepareStatement("update Agendamento set horario_inicio = ?, horario_fim = ?, data = ?, status = ?, cpf = ?, id_quadra = ? where id_agendamento = ?;");
    	select = conexao.prepareStatement("select * from Agendamento where id_agendamento = ?;");
    	selectAll = conexao.prepareStatement("select * from Agendamento;");
    	
    	usuarioDAO = UsuarioDAO.getInstance();
    	quadraDAO = QuadraDAO.getInstance();
    	
    }
    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SelectException("novo id da tabela agendamento");
        }
        return 0;
    }
    
    public int insert(Agendamento agendamento) throws SelectException, InsertException {
        try {
            // Define os valores dos parâmetros na declaração 'insert'
            int id = selectNewId();
        	insert.setInt(1, id);
            insert.setString(2, agendamento.getHorario_inicio());
            insert.setString(3, agendamento.getHorario_fim());
            insert.setString(4, agendamento.getData());
            insert.setString(5, agendamento.getStatus());
            insert.setString(6, agendamento.getUsuario().getCpf());
            insert.setInt(7, agendamento.getQuadra().getId_quadra());
            // Executa o comando de inserção
            insert.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new InsertException("Agendamento");
        }
    }
    
    public void delete(Agendamento agendamento) throws DeleteException{
    	try {
    		delete.setInt(1, agendamento.getId_agendamento());
    		delete.executeUpdate();
    	} catch(SQLException e) {
    		throw new DeleteException("Agendamento");
    	}
    }
    
    public void update(Agendamento agendamento) throws UpdateException {
        try {
            update.setString(1, agendamento.getHorario_inicio());
            update.setString(2, agendamento.getHorario_fim());
            update.setString(3, agendamento.getData());
            update.setString(4, agendamento.getStatus());
            update.setString(5, agendamento.getUsuario().getCpf());
            update.setInt(6, agendamento.getQuadra().getId_quadra());
            update.setInt(7, agendamento.getId_agendamento());
            update.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateException("Agendamento");
        }
    }

    public Agendamento select(int id_agendamento) throws SelectException{
    	try {
    		select.setInt(1, id_agendamento);
    		ResultSet rs = select.executeQuery();
    		
    		if(rs.next()) {
    			String horario_inicio = rs.getString(2);
    			String horario_fim = rs.getString(3);
    			String data = rs.getString(4);
    			String status = rs.getString(5);
    			String cpf = rs.getString(6);
    			int id_quadra = rs.getInt(7);
    			
    			Quadra quadra = quadraDAO.select(id_quadra);
    			Usuario usuario = usuarioDAO.select(cpf);
    			
    			return new Agendamento(id_agendamento, horario_inicio, horario_fim, data, status, usuario, quadra);
    			
    			
    		}
    	}catch (SQLException e) {
    		throw new SelectException("Agendamento");
    	}
    	return null;
    }
    
    public List<Agendamento> selectAll() throws SelectException{
    	List<Agendamento> lista = new LinkedList<>();
    	
    	try {
    		ResultSet rs = selectAll.executeQuery();
    		
    		while(rs.next()) {
    			int id_agendamento = rs.getInt(1);
    			String horario_inicio = rs.getString(2);
    			String horario_fim = rs.getString(3);
    			String data = rs.getString(4);
    			String status = rs.getString(5);
    			String cpf = rs.getString(6);
    			int id_quadra = rs.getInt(7);
    			
    			Quadra quadra = quadraDAO.select(id_quadra);
    			Usuario usuario = usuarioDAO.select(cpf);
    			
    			lista.add(new Agendamento(id_agendamento, horario_inicio, horario_fim, data, status, usuario, quadra));
    			
    		}
    	}catch (SQLException e) {
    		throw new SelectException("Agendamento");
    	}
    	return lista;
    	
    }
    
    


	

}
