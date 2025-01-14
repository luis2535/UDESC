package excecoes;

public class UpdateException extends Exception {
    
    public UpdateException(String tabela){
        super("Ocorreu um erro ao atualizar um(a) " + tabela + "\n");
    }
}