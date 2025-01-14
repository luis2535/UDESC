package br.com.example.gerenciamento.excecoes;

public class InsertException extends Exception {
        
    public InsertException(String tabela){
        super("Ocorreu um erro ao inserir um(a) " + tabela + "\n");
    }

}