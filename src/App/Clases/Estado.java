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
    public List<String> transicionUsada = new ArrayList();
    
    
    public Estado(int estado, String conjunto, String[] listaConj, List<Transicion> transiciones){
        this.estado = estado;
        this.conjunto = conjunto;
        this.listaConj = listaConj;
        this.transiciones = transiciones = new ArrayList();
    }
    
    
    public static void calcularEstados(Estado actual){
        String[] sig;
        //int count=actual.estado;
        int count=actual.estado;
        if(!estadosUsados.contains(actual.conjunto)){
            estadosUsados.add(actual.conjunto);
            List<String> estadoNuevo=null; 
            count++;
            for(int i=0; i<actual.listaConj.length;i++){
                for(int j=0; j<Node.listaTerminales.size();j++){
                    estadoNuevo = new ArrayList(); 
                    for(int t=0; t<Node.misSiguientes.size();t++){
                        
                        if(actual.listaConj[i].equals(Node.misSiguientes.get(t).etiqueta) &&
                           Node.listaTerminales.get(j).equals(Node.misSiguientes.get(t).terminal)){
                           //System.out.println(Node.misSiguientes.get(t).terminal);
                            
                           //AGREGA TODOS LOS SIGUIENTES AL ESTADO NUEVO
                           for(int k=0; k<Node.misSiguientes.get(t).siguientes.size();k++){
                               
                               if(!estadoNuevo.contains(Node.misSiguientes.get(t).siguientes.get(k))){
                                    
                                    estadoNuevo.add(Node.misSiguientes.get(t).siguientes.get(k));
                               }
                           }
                           if(Node.misSiguientes.get(t).terminal.equals("#")){
                               actual.transiciones.add(new Transicion(count,"#"));
                               //System.out.println(Node.misSiguientes.get(t).terminal);
                           }///////////////////////////////////////////
                        }
                    }
                    if(!estadoNuevo.isEmpty()){
                        
                        if(!actual.transicionUsada.contains(Node.listaTerminales.get(j))){
                            if(estadosUsados.contains(String.join(",", estadoNuevo))){
                                actual.transicionUsada.add(Node.listaTerminales.get(j));
                                actual.transiciones.add(new Transicion(estadosUsados.indexOf(String.join(",", estadoNuevo)),Node.listaTerminales.get(j)));
                            }else{
                                actual.transicionUsada.add(Node.listaTerminales.get(j));
                                actual.transiciones.add(new Transicion(count,Node.listaTerminales.get(j)));
                            }
                        }
                        calcularEstados(new Estado(count,String.join(",", estadoNuevo),String.join(",", estadoNuevo).split(","),null));
                    }   
                }
                
            }
            misEstados.add(actual);
        }
    }
    
    public static String graficarAFD(List<Estado> misEstados){
        String dot_afd="";
        int aceptacion = 0;
        for(int i=misEstados.size()-1; i>-1; i--){
            for(int j=misEstados.get(i).transiciones.size()-1; j>-1;j--){
                if(!misEstados.get(i).transiciones.get(j).terminal.equals("#")){
                    dot_afd+="S" + misEstados.get(i).estado+"->"; 
                    dot_afd+="S"+misEstados.get(i).transiciones.get(j).estado+"[label=\""+misEstados.get(i).transiciones.get(j).terminal+"\"];\n";
                }
                if(!misEstados.get(i).transiciones.get(j).terminal.equals("#")){
                    aceptacion=misEstados.get(i).transiciones.get(j).estado;
                }
            }
        }
        
        dot_afd = "digraph finite_state_machine {\n" +
        "rankdir=LR;\n" +
        "size=\"8,5\"\n" +
        "node [shape = doublecircle];S"+aceptacion+";\n" +
        "node [shape = circle];"+dot_afd+"}";
        //System.out.println(dot_afd);
        return dot_afd;
    }
    
    public static String tabularEstados(List<Estado> misEstados, String name){
        String dot="";
        
        String terminales="";
        for(int t=Node.listaTerminales.size()-1; t>0; t--){
            if(!Node.listaTerminales.get(t).equals("#")){
                terminales+="<td>"+Node.listaTerminales.get(t)+"</td>\n";
            }
        }
        String[] trans = null;
        for(int i=misEstados.size()-1; i>-1; i--){
            dot+="<tr><td>S"+misEstados.get(i).estado+"("+misEstados.get(i).conjunto+")</td>\n";
            trans = new String[Node.listaTerminales.size()];
            
            
            for(int t=Node.listaTerminales.size()-1; t>0; t--){
               
                for(int j=misEstados.get(i).transiciones.size()-1; j>-1;j--){
                    if(Node.listaTerminales.get(t).equals(misEstados.get(i).transiciones.get(j).terminal)){
                        trans[t] = Integer.toString(misEstados.get(i).transiciones.get(j).estado);
                    }
                }        
            }
            for(int s=trans.length-1;s>0;s--){
                if(trans[s]!=null){
                    dot+="<td>S"+trans[s]+"</td>\n";
                }else{
                    dot+="<td>---</td>\n";
                }
            }
            
            dot=dot +"</tr>\n";
        }
        
        
        
        String afd="digraph finite_state_machine {\n" +
        "rankdir=LR;\n" +
        "size=\"8,5\"\n" +
        "node [shape = doublecircle]; LR_0 LR_3 LR_4 LR_8;\n" +
        "node [shape = circle];";
             
        String tabla = "digraph G{\n" +
        "graph [fontsize=30 labelloc=\"t\" label=\"\" splines=true overlap=false rankdir = \"LR\"];\n" +
        "\n" +
        "\"state5\" [ style = \"filled\" penwidth = 1 fillcolor=\"#86FE92\" fontname = \"Courier New\" shape = \"Mrecord\" label =\n" +
        "<<table border=\"0\" cellborder=\"1\" cellpadding=\"3\" bgcolor=\"#86FE92\">\n" +
        "<tr><td bgcolor=\"black\" align=\"center\" colspan=\""+Integer.toString(Node.listaTerminales.size())+"\"><font color=\"white\">"+name+"</font></td></tr>\n" +
        "\n" +
        "<tr>\n" +
        "<td align=\"left\">Estado</td>\n" +
        terminales+
        "</tr>\n" +
        "\n" +
        "\n" +
        dot+"\n </table>>];}";
        //System.out.println(tabla);
        return tabla;
    }
   
    
    public static void verEstados(List<Estado> misEstados, String name){
        System.out.println(name);
        for(int i=0; i<misEstados.size(); i++){
            System.out.println(misEstados.get(i).estado);
        
        }
    }
    
}
