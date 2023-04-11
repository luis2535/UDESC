
public class Torcedor {
	
	private int cod_torcedor;
	private String nome = new String();
	private int idade;
	private String nacionalidade = new String();
	private int cod_ingresso;
	private Ingresso ingresso;
	
	public Torcedor(int cod, String nome, int idade, String nacionalidade, int cod_ingresso) {
		this.cod_torcedor = cod;
		this.nome = nome;
		this.idade = idade;
		this.nacionalidade = nacionalidade;
		this.cod_ingresso = cod_ingresso;
	}
	
	public int getCod_torcedor() {
		return cod_torcedor;
	}
	public void setCod_torcedor(int cod_torcedor) {
		this.cod_torcedor = cod_torcedor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public int getCod_ingresso() {
		return cod_ingresso;
	}
	public void setCod_ingresso(int cod_ingresso) {
		this.cod_ingresso = cod_ingresso;
	}
	public Ingresso getIngresso() {
		return ingresso;
	}
	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\nNome: "+nome+", Idade: "+idade+", Nacionalidade: "+nacionalidade+ ", Código Torcedor: " +cod_torcedor+ ", Código Ingresso: "+cod_ingresso);
		if(ingresso != null)
			sb.append(", Nro. Ingresso: "+ingresso.getNro_ingresso()+", Assento: "+ingresso.getAssento()+", Preço: "+ingresso.getPreco());
		sb.append("\n");
		return sb.toString();
	}
	
}
