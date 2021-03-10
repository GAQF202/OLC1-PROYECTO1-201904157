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
public class Fin {
    public int estado;
    public List<String> terminales = new ArrayList();
    
    public Fin(int estado, List<String> terminales){
        this.estado = estado;
        this.terminales = terminales;
    }
}
