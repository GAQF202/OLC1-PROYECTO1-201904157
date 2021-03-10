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
public class Set {
    public String Nombre;
    //String Expresion;
    public List<String> caracteresAcept = new ArrayList();
    
    
    //VARIABLES GLOBALES
    public static List<Set> allSets = new ArrayList();
    
    public Set(String Nombre, List<String> caracteresAcept){
        this.Nombre = Nombre;
        //this.Expresion = Expresion;
        this.caracteresAcept = caracteresAcept;
    }
    
    //CREA EL CONJUNTO Y LO ALMACENA EN LA LISTA GLOBAL DE CONJUNTOS
    public static void createRangeSet(String nombre, String izq, String der){ 
        nombre = nombre.replace("{", "\\{");
        nombre = nombre.replace("}", "\\}");
        List<String> acept = new ArrayList();
        int inferior = izq.charAt(0);
        int superior = der.charAt(0);
        
        for(int i=inferior; i<=superior; i++){
            if(i<65 || i>122){
                acept.add(Character.toString((char)i));
            }
        }
        
        allSets.add(new Set(nombre,acept));
    }
    //CREA EL CONJUNTO DE CARACTERES SEPARADOS POR COMAS
    public static void createListSet(String nombre, String characterList){
        nombre = nombre.replace("{", "\\{");
        nombre = nombre.replace("}", "\\}");
        String[] characters = characterList.split(",");
        List<String> acept = new ArrayList();
        
        
        for(int i=0; i<characters.length;i++){
            acept.add(characters[i]);
        }
        allSets.add(new Set(nombre,acept));
    }
    
    //CREA LOS CONJUNTOS ENTRE LETRAS Y NUMEROS NORMALES
    public static void createSet(String nombre, String izq, String der){ 
        nombre = nombre.replace("{", "\\{");
        nombre = nombre.replace("}", "\\}");
        List<String> acept = new ArrayList();
        int inferior = izq.charAt(0);
        int superior = der.charAt(0);
        
        for(int i=inferior; i<=superior; i++){
            acept.add(Character.toString((char)i));
        }
        
        allSets.add(new Set(nombre,acept));
    }
}
