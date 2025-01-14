package dados;





public class Agendamento {
	private int id_agendamento;
	private String horario_inicio;
	private String horario_fim;
	private String data;
	private String status;
	private Usuario usuario;
	private Quadra quadra;
	
	public Agendamento() {}
	
	public Agendamento(int id_agendamento, String horario_inicio, String horario_fim, String data, String status, Usuario usuario, Quadra quadra) {
		this.id_agendamento = id_agendamento;
		this.horario_inicio = horario_inicio;
		this.horario_fim = horario_fim;
		this.data = data;
		this.status = status;
		this.usuario = usuario;
		this.quadra = quadra;

	}


	public int getId_agendamento() {
		return id_agendamento;
	}

	public void setId_agendamento(int id_agendamento) {
		this.id_agendamento = id_agendamento;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Quadra getQuadra() {
		return quadra;
	}

	public void setQuadra(Quadra quadra) {
		this.quadra = quadra;
	}

	@Override
	public String toString() {
		return "Agendamento [id_agendamento=" + id_agendamento + ", horario_inicio=" + horario_inicio + ", horario_fim="
				+ horario_fim + ", data=" + data + ", status=" + status + ", usuario=" + usuario + ", quadra=" + quadra
				+ "]";
	}
	
	
}
