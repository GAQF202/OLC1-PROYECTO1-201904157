/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gerson
 */
public class Inicio {
    public String nombre;
    public Fin transicion;
    
    public static List<Inicio> estates = new ArrayList();
    
    public Inicio(String nombre, Fin transicion){
        this.nombre = nombre;
        this.transicion = transicion;
    }
}

