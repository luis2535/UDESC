
public class Ingresso {
	private int cod_ingresso;
	private int assento;
	private float preco;
	private int nro_ingresso;
	
	public Ingresso(int cod_i,int assent,float prec,int nro_i) {
		this.cod_ingresso = cod_i;
		this.assento = assent;
		this.preco = prec;
		this.nro_ingresso = nro_i;
	}
	
	public int getCod_ingresso() {
		return cod_ingresso;
	}
	public void setCod_ingresso(int cod_ingresso) {
		this.cod_ingresso = cod_ingresso;
	}
	public int getAssento() {
		return assento;
	}
	public void setAssento(int assento) {
		this.assento = assento;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public int getNro_ingresso() {
		return nro_ingresso;
	}
	public void setNro_ingresso(int nro_ingresso) {
		this.nro_ingresso = nro_ingresso;
	}
	public String toString() {
		return "\nNro. Ingresso: "+nro_ingresso+", Assento: "+assento+", Preço: "+preco +", Cod_ingresso: "+cod_ingresso+"\n";
	}
	
}
