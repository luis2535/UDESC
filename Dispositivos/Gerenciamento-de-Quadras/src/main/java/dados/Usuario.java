
package dados;

public class Usuario {
	private String cpf;
	private String pnome;
	private String unome;
	private String email;
	private String senha;
	private String status;

	public Usuario() {}
	
	public Usuario(String cpf, String pnome, String unome, String email, String senha, String status) {
		this.cpf = cpf;
		this.pnome = pnome;
		this.unome = unome;
		this.email = email;
		this.senha = senha;
		this.status = status;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPnome() {
		return pnome;
	}

	public void setPnome(String pnome) {
		this.pnome = pnome;
	}

	public String getUnome() {
		return unome;
	}

	public void setUnome(String unome) {
		this.unome = unome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String toString() {
		return "CPF: "+cpf+" Nome: "+pnome+" Sobrenome: "+unome+" email: "+email+" Senha: "+senha+"Status: "+status;
	}
}
