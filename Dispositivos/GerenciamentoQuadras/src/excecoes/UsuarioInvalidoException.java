/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excecoes;

/**
 *
 * @author luis2535
 */
public class UsuarioInvalidoException extends Exception {
    public UsuarioInvalidoException(){
    }
    public UsuarioInvalidoException(String msg){
        super(msg);
    }
    
}
