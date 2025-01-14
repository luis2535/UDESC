package negocios;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import dados.*;
import persistencia.*;
import excecoes.*;


public class Sistema {
	private static Sistema instance = null;
	private static UsuarioDAO usuarioDAO = null;
	private static BolsistaDAO bolsistaDAO = null;
	private static ServidorDAO servidorDAO = null;
	private static BlocoDAO blocoDAO = null;
	private static QuadraDAO quadraDAO = null;
	private static AgendamentoDAO agendamentoDAO = null;
	private static EquipamentoDAO equipamentoDAO = null;
	private static EventoDAO eventoDAO = null;
	private static ReservaDAO reservaDAO = null;
	
	public static Sistema getInstance(){
        if (instance == null) {
            try {
                instance = new Sistema();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
	private Sistema() throws ClassNotFoundException, SQLException{
		usuarioDAO = UsuarioDAO.getInstance();
		bolsistaDAO = BolsistaDAO.getInstance();
		servidorDAO = ServidorDAO.getInstance();
		blocoDAO = BlocoDAO.getInstance();
		quadraDAO = QuadraDAO.getInstance();
		agendamentoDAO = AgendamentoDAO.getInstance();
		equipamentoDAO = EquipamentoDAO.getInstance();
		reservaDAO = ReservaDAO.getInstance();
		eventoDAO = EventoDAO.getInstance();
	}
	//----------------------------Usuario----------------------------//
	public void insereUsuario(Usuario usuario) throws SelectException, InsertException{
		usuarioDAO.insert(usuario);
	}
	public void deletaUsuario(Usuario usuario) throws DeleteException {
		usuarioDAO.delete(usuario);
	}
	public void atualizaUsuario(Usuario usuario) throws UpdateException {
		usuarioDAO.update(usuario);
	}
	public Usuario buscaUsuario(String cpf) throws SelectException {
		return usuarioDAO.select(cpf);
	}
	public List<Usuario> buscaUsuarios() throws SelectException{
		return usuarioDAO.selectAll();
	}
	//----------------------------Bolsista----------------------------//
	public void insereBolsista(Bolsista bolsista) throws SelectException, InsertException, UpdateException {
		bolsistaDAO.insert(bolsista);
	}
	public void deletaBolsista(Bolsista bolsista) throws DeleteException {
		bolsistaDAO.delete(bolsista);
	}
	public void atualizaBolsista(Bolsista bolsista) throws UpdateException {
		bolsistaDAO.update(bolsista);
		usuarioDAO.update(bolsista);
	}
	public Bolsista buscaBolsista(String cpf) throws SelectException {
		return bolsistaDAO.select(cpf);
		
	}
	public List<Bolsista> buscaBolsistas() throws SelectException{
		return bolsistaDAO.selectAll();
	}
	
	//----------------------------Servidor----------------------------//
	public void insereServidor(Servidor servidor) throws SelectException, InsertException, UpdateException {
		servidorDAO.insert(servidor);
	}
	public void deletaServidor(Servidor servidor) throws DeleteException {
		servidorDAO.delete(servidor);
	}
	public void atualizaServidor(Servidor servidor) throws UpdateException {
		servidorDAO.update(servidor);
		usuarioDAO.update(servidor);
	}
	public Servidor buscaServidor(String cpf) throws SelectException {
		return servidorDAO.select(cpf);
		
	}
	public List<Servidor> buscaServidores() throws SelectException{
		return servidorDAO.selectAll();
	}
	//----------------------------Bloco----------------------------//
	public void insereBloco(Bloco bloco) throws SelectException, InsertException{
		blocoDAO.insert(bloco);
	}
	public void deletaBloco(Bloco bloco) throws DeleteException{
		blocoDAO.delete(bloco);
	}
	public void atualizaBloco(Bloco bloco) throws UpdateException{
		blocoDAO.update(bloco);
	}
	public Bloco buscaBloco(int id_bloco) throws SelectException{
		return blocoDAO.select(id_bloco);
	}
	public List<Bloco> buscaBlocos() throws SelectException{
		return blocoDAO.selectAll();
	}
	//----------------------------Quadra----------------------------//
	public void insereQuadra(Quadra quadra) throws SelectException, InsertException{
		quadraDAO.insert(quadra);
	}
	public void deletaQuadra(Quadra quadra) throws DeleteException{
		quadraDAO.delete(quadra);
	}
	public void atualizaQuadra(Quadra quadra) throws UpdateException{
		quadraDAO.update(quadra);
	}
	public Quadra buscaQuadra(int id_quadra) throws SelectException{
		return quadraDAO.select(id_quadra);
	}
	public List<Quadra> buscaQuadras() throws SelectException{
		return quadraDAO.selectAll();
	}
	//----------------------------Agendamento----------------------------//
	public void insereAgendamento(Reserva reserva) throws SelectException, InsertException{
		int id = agendamentoDAO.insert(reserva.getAgendamento());
		reserva.getAgendamento().setId_agendamento(id);
		reservaDAO.insert(reserva);
	}
	public void deletaAgendamento(Reserva reserva) throws DeleteException{
		reservaDAO.delete(reserva);
		agendamentoDAO.delete(reserva.getAgendamento());
	}
	public void atualizaAgendamento(Agendamento agendamento) throws UpdateException{
		agendamentoDAO.update(agendamento);
	}
	public Agendamento buscaAgendamento(int id_agendamento) throws SelectException{
		return agendamentoDAO.select(id_agendamento);
	}
	public List<Agendamento> buscaAgendamentos() throws SelectException{
		return agendamentoDAO.selectAll();
	}
	//----------------------------Equipamento----------------------------//
	public void insereEquipamento(Equipamento equipamento) throws SelectException, InsertException {
		equipamentoDAO.insert(equipamento);
	}
	public void deletaEquipamento(Equipamento equipamento) throws DeleteException{
		equipamentoDAO.delete(equipamento);
	}
	public void atualizaEquipamento(Equipamento equipamento) throws UpdateException{
		equipamentoDAO.update(equipamento);
	}
	public Equipamento buscaEquipamento(int id_equipamento) throws SelectException{
		return equipamentoDAO.select(id_equipamento);
	}	
	public List<Equipamento> buscaEquipamentos() throws SelectException{
		return equipamentoDAO.selectAll();
	}
	//----------------------------Evento----------------------------//
	public void insereEvento(Evento evento) throws InsertException, SelectException{
		int id = eventoDAO.insert(evento);
		evento.setId_evento(id);
		List<Quadra> quadras = evento.getQuadras();
		for(Quadra q : quadras) {
			eventoDAO.insertQuadraEvento(evento, q);
		}
	}
	public void deleteEvento(Evento evento) throws DeleteException{
		List<Quadra> quadras = evento.getQuadras();
		for(Quadra q : quadras) {
			
			eventoDAO.deleteQuadraEvento(evento, q);
		}
		eventoDAO.delete(evento);
	}
	public void deleteQuadraEvento(Evento evento, Quadra quadra) throws DeleteException{
		eventoDAO.deleteQuadraEvento(evento, quadra);
	}
	public void atualizaEvento(Evento evento) throws UpdateException {
		eventoDAO.update(evento);
	}
	public Evento buscaEvento(int id_evento) throws SelectException{
		return eventoDAO.select(id_evento);
	}
	public List<Evento> buscaEventos() throws SelectException{
		return eventoDAO.selectAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
