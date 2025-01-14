package dados;

public class Quadra {

	private int id_quadra;
	private String modalidade;
	private String descricao;
	private Bloco id_bloco;
	
	public Quadra(){}
	
	public Quadra(int id_quadra, String modalidade, String descricao, Bloco id_bloco) {
		this.id_quadra = id_quadra;
		this.modalidade = modalidade;
		this.descricao = descricao;
		this.id_bloco = id_bloco;
	}

	public int getId_quadra() {
		return id_quadra;
	}

	public void setId_quadra(int id_quadra) {
		this.id_quadra = id_quadra;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Bloco getId_bloco() {
		return id_bloco;
	}

	public void setId_bloco(Bloco id_bloco) {
		this.id_bloco = id_bloco;
	}

	public String toString() {
		return "Quadra [id_quadra=" + id_quadra + ", modalidade=" + modalidade + ", descricao=" + descricao
				+ ", id_bloco=" + id_bloco + "]";
	}
	
}
