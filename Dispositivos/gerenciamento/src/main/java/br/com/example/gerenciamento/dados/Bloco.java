package br.com.example.gerenciamento.dados;

public class Bloco {
	private int id_bloco;
	private String nome;
	private String descricao;
	
	public Bloco() {}
	
	public Bloco(int id_bloco, String nome, String descricao) {
		this.id_bloco = id_bloco;
		this.nome = nome;		
		this.descricao = descricao;
		
	}

	public int getId_bloco() {
		return id_bloco;
	}

	public void setId_bloco(int id_bloco) {
		this.id_bloco = id_bloco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return "id_bloco=" + id_bloco + ", nome=" + nome + ", descricao=" + descricao;
	}

}
