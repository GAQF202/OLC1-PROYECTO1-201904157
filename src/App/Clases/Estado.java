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
public class Estado {
    public int estado;
    public String conjunto;
    public String[] listaConj;
    public List<Transicion> transiciones = new ArrayList();
    
    public static List<Estado> misEstados = new ArrayList();
    public static List<String> estadosUsados = new ArrayList();
    
    
    public Estado(int estado, String conjunto, String[] listaConj, List<Transicion> transiciones){
        this.estado = estado;
        this.conjunto = conjunto;
        this.listaConj = listaConj;
        this.transiciones = transiciones = new ArrayList();
    }
    
    
    public static void calcularEstados(Estado actual){
        String[] sig;
        int count=actual.estado;
        
        if(!estadosUsados.contains(actual.conjunto)){
            estadosUsados.add(actual.conjunto);

            List<String> estadoNuevo=null; 
            count++;
            for(int i=0; i<actual.listaConj.length;i++){
                for(int j=0; j<Node.listaTerminales.size();j++){
                    estadoNuevo = new ArrayList(); 
                    for(int t=0; t<Node.misSiguientes.size();t++){
                       //estadoNuevo = new ArrayList(); 
                        /*System.out.println(Node.listaTerminales.get(j));
                        System.out.println(Node.misSiguientes.get(t).terminal);
                        System.out.println("----------------------------------");*/
                        if(actual.listaConj[i].equals(Node.misSiguientes.get(t).etiqueta) &&
                           Node.listaTerminales.get(j).equals(Node.misSiguientes.get(t).terminal)){
                            
                           //AGREGA TODOS LOS SIGUIENTES AL ESTADO NUEVO
                           for(int k=0; k<Node.misSiguientes.get(t).siguientes.size();k++){
                               if(!estadoNuevo.contains(Node.misSiguientes.get(t).siguientes.get(k))){
                                    //System.out.println(Node.misSiguientes.get(t).siguientes.get(k));
                                    estadoNuevo.add(Node.misSiguientes.get(t).siguientes.get(k));
                               }
                           }
                           //actual.transiciones.add(new Transicion(actual.estado+1,Node.misSiguientes.get(t).terminal));
                        
                        }
                    }
                    if(!estadoNuevo.isEmpty()){
                        //count++;
                        /*System.out.println(count);
                        System.out.println(estadoNuevo);*/
                        if(estadosUsados.contains(String.join(",", estadoNuevo))){
                            //System.out.println(estadosUsados.indexOf(String.join(",", estadoNuevo)));
                            actual.transiciones.add(new Transicion(estadosUsados.indexOf(String.join(",", estadoNuevo)),Node.listaTerminales.get(j)));
                        }else{
                            /*if(String.join(",", estadoNuevo).equals(actual.conjunto)){
                                actual.transiciones.add(new Transicion(actual.estado,Node.listaTerminales.get(j)));
                            }else{*/
                            actual.transiciones.add(new Transicion(count,Node.listaTerminales.get(j)));
                            //}
                        }
                        calcularEstados(new Estado(count,String.join(",", estadoNuevo),String.join(",", estadoNuevo).split(","),null));
                    }
                }
                
            }
            misEstados.add(actual);
        }
    }
    
    public static void tabularEstados(List<Estado> misEstados){
        for(int i=0; i<misEstados.size(); i++){
            System.out.println(misEstados.get(i).estado+"-------");
            for(int j=0; j<misEstados.get(i).transiciones.size();j++){
                System.out.println(misEstados.get(i).transiciones.get(j).terminal);
            }
        }
    }
}
