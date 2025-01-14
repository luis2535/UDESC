package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Evento;
import dados.Servidor;
import dados.Quadra;
import excecoes.DeleteException;
import excecoes.InsertException;
import excecoes.SelectException;
import excecoes.UpdateException;

public class EventoDAO {
	private static EventoDAO instance = null;
	private static ServidorDAO servidorDAO = null;
	private static QuadraDAO quadraDAO = null;
	
	private PreparedStatement selectNewId;
	private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement selectQuadraEvento;
    private PreparedStatement select;
    private PreparedStatement selectAll;
    private PreparedStatement insertQuadraEvento;
    private PreparedStatement deleteQuadraEvento;
    
    public static EventoDAO getInstance() throws ClassNotFoundException, SQLException{
    	if(instance == null) {
    		instance = new EventoDAO();
    	}
    	return instance;
    }
    
    private EventoDAO() throws ClassNotFoundException, SQLException{
    	Connection conexao = Conexao.getConexao();
    	selectNewId = conexao.prepareStatement("select nextval('evento_id_seq');");
    	insert = conexao.prepareStatement("insert into Evento values(?, ?, ?, ?, ?, ?, ?);");
    	delete = conexao.prepareStatement("delete from Evento  where id_evento = ?;");
    	update = conexao.prepareStatement("update Evento  set data = ?, horario_inicio = ?, horario_fim = ?, status = ?, nome = ?, cpf = ? where id_evento = ?;");
    	selectQuadraEvento = conexao.prepareStatement("select id_quadra from quadra join QuadraEvento using (id_quadra) where id_evento = ?;");
    	select = conexao.prepareStatement("select * from Evento  where id_evento = ?;");
    	selectAll = conexao.prepareStatement("select * from Evento ;");
    	insertQuadraEvento = conexao.prepareStatement("insert into QuadraEvento values (?, ?);");
    	deleteQuadraEvento = conexao.prepareStatement("delete from QuadraEvento where id_evento = ? and id_quadra = ?;");
    	
    	servidorDAO = ServidorDAO.getInstance();
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
    
    public int insert(Evento evento) throws SelectException, InsertException {
        try {
            // Define os valores dos parâmetros na declaração 'insert'
        	int idEvento = selectNewId();
            insert.setInt(1, idEvento);
            insert.setString(2, evento.getData());
            insert.setString(3, evento.getHorario_inicio());
            insert.setString(4, evento.getHorario_fim());
            insert.setString(5, evento.getStatus());
            insert.setString(6, evento.getNome());
            insert.setString(7, evento.getServidor().getCpf());
            // Executa o comando de inserção
            insert.executeUpdate();
            
            return idEvento;
        } catch (SQLException e) {
            throw new InsertException("Evento");
        }
    }
    
    public void delete(Evento evento) throws DeleteException{
    	try {
    		delete.setInt(1, evento.getId_evento());
    		delete.executeUpdate();
    	} catch(SQLException e) {
    		throw new DeleteException("Evento");
    	}
    }
    
    public void update(Evento evento) throws UpdateException {
        try {
        	update.setString(1, evento.getData());
            update.setString(2, evento.getHorario_inicio());
            update.setString(3, evento.getHorario_fim());
            update.setString(4, evento.getStatus());
            update.setString(5, evento.getNome());
            update.setString(6, evento.getServidor().getCpf());
            update.setInt(7, evento.getId_evento());
            update.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateException("Evento");
        }
    }
    
    ////////////////Preciso arrumar a partir daqui

    
  public List<Quadra> selectQuadraEvento(int id_evento) throws SelectException {
	  List<Quadra> quadras = new LinkedList<>();
	  try {
		  selectQuadraEvento.setInt(1, id_evento);
		  ResultSet rs = selectQuadraEvento.executeQuery();
		  
		  while(rs.next()) {
			  int id_quadra = rs.getInt(1);
			  Quadra quadra = quadraDAO.select(id_quadra);
			  
			  quadras.add(quadra);
		  }
	  }catch(SQLException e) {
		  throw new SelectException("Eventos");
	  }
	  return quadras;
  }
  public Evento select (int id_evento) throws SelectException{
	  
	  try {
		  select.setInt(1, id_evento);
		  ResultSet rs = select.executeQuery();
		  
		  if(rs.next()) {
			  String data = rs.getString(2);
			  String horario_inicio = rs.getString(3);
			  String horario_fim = rs.getString(4);
			  String status = rs.getString(5);
			  String nome = rs.getString(6);
			  String cpf = rs.getString(7);
			 
			  Servidor servidor = servidorDAO.select(cpf);
			  List<Quadra> quadras = this.selectQuadraEvento(id_evento);
			 
			  return new Evento(id_evento, data, horario_inicio, horario_fim, status, nome, servidor, quadras);
		  }
		  
	  }catch (SQLException e) {
		  throw new SelectException("Evento");
	  }
	  return null;
  } 
  
  public List<Evento> selectAll() throws SelectException{
	  List<Evento> lista = new LinkedList<>();
	  
	  try {
		  ResultSet rs = selectAll.executeQuery();
		  
		  while(rs.next()) {
			  int id_evento = rs.getInt(1);
			  String data = rs.getString(2);
			  String horario_inicio = rs.getString(3);
			  String horario_fim = rs.getString(4);
			  String status = rs.getString(5);
			  String nome = rs.getString(6);
			  String cpf = rs.getString(7);
			  
			  Servidor servidor = servidorDAO.select(cpf);
			  List<Quadra> quadras = this.selectQuadraEvento(id_evento);
			  lista.add(new Evento(id_evento, data, horario_inicio, horario_fim, status, nome, servidor, quadras));
		  }
	  }catch (SQLException e) {
		  throw new SelectException("Evento");
	  }
	  return lista;
  }
  
  public void insertQuadraEvento(Evento evento, Quadra quadra) throws InsertException {
	    try {
	        insertQuadraEvento.setInt(1, evento.getId_evento());
	        insertQuadraEvento.setInt(2, quadra.getId_quadra());
	        insertQuadraEvento.executeUpdate();
	    } catch (SQLException e) {
	        throw new InsertException("Quadras para evento: " + e.getMessage());
	    }
	}


  
  public void deleteQuadraEvento(Evento evento, Quadra quadra) throws DeleteException{
	  try {
		  deleteQuadraEvento.setInt(1, evento.getId_evento());
		  deleteQuadraEvento.setInt(2, quadra.getId_quadra());
		  deleteQuadraEvento.executeUpdate();
	  }catch(SQLException e) {
		  throw new DeleteException("QuadraEvento");
	  }
  }
    
    	
    
    

}
