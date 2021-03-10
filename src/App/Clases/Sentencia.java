/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Clases;

import App.Principal;

/**
 *
 * @author Gerson
 */
public class Sentencia {
    public String nombre;
    public String entrada;
    
    public Sentencia(String nombre, String entrada){
        this.entrada = entrada;
        this.nombre = nombre;
    }
    
    public static void guardarSentencia(String nombre, String entrada){
        Principal.Sentencias.add(new Sentencia(nombre,entrada));
    }
    
}
