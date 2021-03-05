/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Clases;

/**
 *
 * @author Gerson
 */
public class Transicion {
    
    public int estado;
    public String terminal;
    
    public Transicion(int estado, String terminal){
        this.estado = estado;
        this.terminal = terminal;
    }
    
}
