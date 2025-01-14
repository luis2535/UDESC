package excecoes;

public class SelectException extends Exception {
    
    public SelectException(String tabela){
        super("Ocorreu um erro ao selecionar um(a) " + tabela + "\n");
    }
}