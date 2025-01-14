package com.example.trab.excecoes;

public class DeleteException extends Exception {
    
    public DeleteException(String tabela){
        super("Ocorreu um erro ao deletar um(a) " + tabela + "\n");
    }
}