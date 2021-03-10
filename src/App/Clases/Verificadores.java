/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Clases;

import java.util.ArrayList;
import java.util.List;
import App.Principal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static jflex.Skeleton.line;

/**
 *
 * @author Gerson
 */
public class Verificadores {
    public String nombre;
    public List<Estado> tablaEstados;
    public static List<Verificadores> verificador = new ArrayList();
    public static List<String> json = new ArrayList();
    
    public Verificadores(String nombre, List<Estado> tablaEstados){
        this.nombre = nombre;
        this.tablaEstados = tablaEstados;
    }
    
   //SE AGREGAN LOS CONJUNTOS A LOS ESTADOS
    public static void agregarConjuntos(){
        
        for(int i=0; i< verificador.size(); i++){
            for(int j=0; j<verificador.get(i).tablaEstados.size();j++){
                for(int k=0; k<verificador.get(i).tablaEstados.get(j).transiciones.size();k++){ 
                    for(int l=0; l<Set.allSets.size();l++){
                        if(Set.allSets.get(l).Nombre.equals(verificador.get(i).tablaEstados.get(j).transiciones.get(k).terminal)){
                            verificador.get(i).tablaEstados.get(j).transiciones.get(k).aceptados = Set.allSets.get(l).caracteresAcept;
                        }
                    }
                }
                
            }  
        }

    }
    
    public static Estado encontrarVerificador(String nombre){
        Estado encontrado=null;
        for(int i=0; i<verificador.size();i++){
            if(verificador.get(i).nombre.equals(nombre)){
                encontrado = verificador.get(i).tablaEstados.get(verificador.get(i).tablaEstados.size()-1);
            }
        }
        return encontrado;
    }
    
    public static void validar(Estado actual, String entrada, int count, String nombre){
        String caracterAct = String.valueOf(entrada.charAt(count));
        Estado siguiente = null;
        String result = "";
        
        if(actual!=null){
            for(int i=0; i<actual.transiciones.size();i++){
                //System.out.println(actual.transiciones.get(i).terminal);
                    if(actual.transiciones.get(i).aceptados.contains(caracterAct)||actual.transiciones.get(i).terminal.equals(caracterAct)){
                        //System.out.println(actual.transiciones.get(i).terminal);
                        //System.out.println(caracterAct);
                        //System.out.println(count);
                        siguiente=encontrarEstado(nombre,actual.transiciones.get(i).estado);         
                }
            }
            if(siguiente!=null){
                //System.out.println(siguiente.estado);
                count++;
                validar(siguiente,entrada,count,nombre);
            }else{
                if(count==entrada.length()-1){
                    entrada = entrada.replaceFirst(".$","");
                    json.add("{Valor :"+nombre+",\n"+
                            "Expresion Regular: "+entrada+",\n"+
                            "Resultado:Cadena válida}\n"
                    );
                    Principal.result += "La expresión " + entrada +" es válida con la expresión Regular: "+ nombre +"\n";
                }else{
                    entrada = entrada.replaceFirst(".$","");
                    /*json.add("{Valor :"+nombre+",\n"+
                            "Expresion Regular: "+entrada+",\n"+
                            "Resultado:Cadena inválida}\n"
                    );*/                    
                    Principal.result += "La expresión " + entrada +" no es válida con la expresión Regular: "+ nombre +"\n";
                } 
            }
        }
    }
    
    public static void verRespuesta(){
        for(int i=0; i< json.size();i++){
            System.out.println(json.get(i));
        }
    }
    
    public static Estado encontrarEstado(String nombre,int estado){
        Estado encontrado=null;
        for(int i=0; i<verificador.size();i++){
            if(verificador.get(i).nombre.equals(nombre)){
                for(int j=0; j<verificador.get(i).tablaEstados.size();j++){
                    if(verificador.get(i).tablaEstados.get(j).estado==estado){
                        encontrado = verificador.get(i).tablaEstados.get(j);
                    }
                }
            }
        }
        return encontrado;
    }
    
    public static void val(){
        
        for(int i=0; i< verificador.size(); i++){
            for(int j=0; j<verificador.get(i).tablaEstados.size();j++){
                System.out.println(verificador.get(i).tablaEstados.get(j).estado + "----");
                for(int k=0; k<verificador.get(i).tablaEstados.get(j).transiciones.size();k++){ 
                    System.out.println(verificador.get(i).tablaEstados.get(j).transiciones.get(k).terminal);
                    System.out.println(verificador.get(i).tablaEstados.get(j).transiciones.get(k).estado);
                    System.out.println(verificador.get(i).tablaEstados.get(j).transiciones.get(k).aceptados);
                }
                
            }
        }
    }
    

}
