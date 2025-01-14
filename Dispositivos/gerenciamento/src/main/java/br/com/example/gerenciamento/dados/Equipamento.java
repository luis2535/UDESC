package br.com.example.gerenciamento.dados;

public class Equipamento {
	private int id_equipamento;
	private String tipo;
	private String descricao;
	
	
	public Equipamento() {}
	public Equipamento(int id_equipamento, String tipo, String descricao) {
		this.id_equipamento = id_equipamento;
		this.tipo = tipo;
		this.descricao = descricao;
	}
	public int getId_equipamento() {
		return id_equipamento;
	}
	public void setId_equipamento(int id_equipamento) {
		this.id_equipamento = id_equipamento;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Equipamento [id_equipamento=" + id_equipamento + ", tipo=" + tipo + ", descricao=" + descricao + "]";
	}
	
	
	

}
