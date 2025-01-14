package dados;


import java.sql.Time;
import java.util.Date;
import java.util.List;


public class Evento {
	private int id_evento;
	private String data;
	private String horario_inicio;
	private String horario_fim;
	private String status;
	private String nome;
	private Servidor usuario;
	private List<Quadra> quadras;
	
	public Evento() {}

	public Evento(int id_evento, String data, String horario_inicio, String horario_fim, String status, String nome, Servidor usuario, List <Quadra> quadras) {
		this.id_evento = id_evento;
		this.data = data;
		this.horario_inicio = horario_inicio;
		this.horario_fim = horario_fim;
		this.status = status;
		this.nome = nome;
		this.usuario = usuario;
		this.quadras = quadras;
	}

	public int getId_evento() {
		return id_evento;
	}

	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario_inicio() {
		return horario_inicio;
	}

	public void setHorario_inicio(String horario_inicio) {
		this.horario_inicio = horario_inicio;
	}

	public String getHorario_fim() {
		return horario_fim;
	}

	public void setHorario_fim(String horario_fim) {
		this.horario_fim = horario_fim;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Servidor getServidor() {
		return usuario;
	}

	public void setServidor(Servidor usuario) {
		this.usuario = usuario;
	}

	public List<Quadra> getQuadras() {
		return quadras;
	}

	public void setQuadras(List<Quadra> quadras) {
		this.quadras = quadras;
	}
	@Override
	public String toString() {
		return "Evento [id_evento=" + id_evento + ", data=" + data + ", horario_inicio=" + horario_inicio
				+ ", horario_fim=" + horario_fim + ", status=" + status + ", nome=" + nome + ", usuario=" + usuario
				+ ", quadras=" + quadras + "]";
	}


}
