package br.com.example.gerenciamento.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.example.gerenciamento.dados.*;
import br.com.example.gerenciamento.excecoes.*;

public class ReservaDAO {
	private static ReservaDAO instance = null;
	private static EquipamentoDAO equipamentoDAO = null;
	private static AgendamentoDAO agendamentoDAO = null;
	
	private PreparedStatement selectNewId;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement select;
    private PreparedStatement selectAll;
    
    public static ReservaDAO getInstance() throws ClassNotFoundException, SQLException{
    	if(instance == null) {
    		instance = new ReservaDAO();
    	}
    	return instance;
    	
    }
    
    private ReservaDAO() throws ClassNotFoundException, SQLException{
    	Connection conexao = Conexao.getConexao();
    	selectNewId = conexao.prepareStatement("select nextval ('reserva_id_seq');");
    	insert = conexao.prepareStatement("insert into Reserva values (?, ?, ?, ?);");
    	delete = conexao.prepareStatement("delete from Reserva where id_agendamento = ?;");
    	select = conexao.prepareStatement("select * from Reserva;");
    	selectAll = conexao.prepareStatement("select * from Reserva;");
    	
    	equipamentoDAO = EquipamentoDAO.getInstance();
    	agendamentoDAO = AgendamentoDAO.getInstance();
    }
    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SelectException("novo id da tabela Reserva");
        }
        return 0;
    }
    
    public void insert(Reserva reserva) throws InsertException, SelectException{
    	try {
    		insert.setInt(1, selectNewId());
    		insert.setInt(2, reserva.getAgendamento().getId_agendamento());
    		insert.setInt(3, reserva.getEquipamento().getId_equipamento());
    		insert.setInt(4, reserva.getQuantidade_equip());
    		insert.executeUpdate();
    	}catch(SQLException e) {
    		throw new InsertException("Reserva");
    	}
    }
    public void delete(Reserva reserva) throws DeleteException{
    	try {
    		delete.setInt(1, reserva.getAgendamento().getId_agendamento());
    		delete.executeUpdate();
    	}catch (SQLException e) {
    		throw new DeleteException("Reserva");
    	}
    }
    public List<Reserva> select(int id_agendamento) throws SelectException{
    	List<Reserva> reserva = new LinkedList<>();
    	try {
    		
    		ResultSet rs = select.executeQuery();
    		while(rs.next()) {
    			int id_reserva = rs.getInt(1);
    			int id_agenda = rs.getInt(2);
    			int id_equipamento = rs.getInt(3);
    			int quantidade_equip = rs.getInt(4);
    			
    			if(id_agenda == id_agendamento) {
    				Agendamento agenda = agendamentoDAO.select(id_agendamento);
    				Equipamento equipamento = equipamentoDAO.select(id_equipamento);
    				reserva.add(new Reserva(id_reserva, agenda, equipamento, quantidade_equip));
    			}
   
    			
    		}
    	}catch(SQLException e) {
    		throw new SelectException("Reserva");
    	}
    	return reserva;
    }
    public List<Reserva> selectAll() throws SelectException{
    	List<Reserva> reserva = new LinkedList<>();
    	try {
    		
    		ResultSet rs = select.executeQuery();
    		while(rs.next()) {
    			int id_reserva = rs.getInt(1);
    			int id_agendamento = rs.getInt(2);
    			int id_equipamento = rs.getInt(3);
    			int quantidade_equip = rs.getInt(4);
    			
				Agendamento agenda = agendamentoDAO.select(id_agendamento);
				Equipamento equipamento = equipamentoDAO.select(id_equipamento);
				reserva.add(new Reserva(id_reserva, agenda, equipamento, quantidade_equip));
			
    			
    		}
    	}catch(SQLException e) {
    		throw new SelectException("Reserva");
    	}
    	return reserva;
    }
}
