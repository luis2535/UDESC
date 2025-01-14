package dados;

public class Servidor extends Usuario{

	private int id_servidor;
	private String funcao;
	
	public Servidor() {}
	
	public Servidor(int id_servidor, String funcao){
		this.id_servidor = id_servidor;
		this.funcao = funcao;
	}
	public Servidor(String cpf, String pnome, String unome, String email, String senha, String status) {
		super(cpf, pnome, unome, email, senha, status);
	}
	public Servidor(String cpf, String pnome, String unome, String email, String senha, String status, int id_servidor, String funcao) {
		super(cpf, pnome, unome, email, senha, status);
		this.id_servidor = id_servidor;
		this.funcao = funcao;
	}

	public int getId_servidor() {
		return id_servidor;
	}

	public void setId_servidor(int id_servidor) {
		this.id_servidor = id_servidor;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public String toString() {
	    return "cpf='" + getCpf() + "', pnome='" + getPnome() + "', unome='" + getUnome() + "', email='" + getEmail() + "', senha='" + getSenha() + "', id_servidor=" + id_servidor + ", funcao='" + funcao + "'}";
	}
}
