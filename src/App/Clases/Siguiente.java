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
public class Siguiente {
 
    
    public String terminal;
    public String etiqueta;
    public List<String> siguientes = new ArrayList(); 
    
    public Siguiente(String terminal, String etiqueta){
        this.terminal = terminal;
        this.etiqueta = etiqueta;
    }
    
    public static void tabularSiguientes(List<Siguiente> listaSig){
        String dot="";
        for(int i=listaSig.size()-1; i>-1;i--){
            listaSig.get(i).terminal=listaSig.get(i).terminal.replace("{", "\\{");
            listaSig.get(i).terminal=listaSig.get(i).terminal.replace("}", "\\}");
            dot+="<tr><td>"+ listaSig.get(i).terminal +"</td>\n";
            dot+="<td>"+ listaSig.get(i).etiqueta +"</td>\n";
            dot+="<td>"+ listaSig.get(i).siguientes +"</td></tr>\n";
        }
        
        String tabla="digraph G{\n" +
        "graph [fontsize=30 labelloc=\"t\" label=\"\" splines=true overlap=false rankdir = \"LR\"];\n" +
        "\n" +
        "\"state5\" [ style = \"filled\" penwidth = 1 fillcolor=\"#86FE92\" fontname = \"Courier New\" shape = \"Mrecord\" label =\n" +
        "<<table border=\"0\" cellborder=\"1\" cellpadding=\"3\" bgcolor=\"#86FE92\">\n" +
        "<tr><td bgcolor=\"black\" align=\"center\" colspan=\"3\"><font color=\"white\">ExpReg1</font></td></tr>\n" +
        "\n" +
        "<tr>\n" +
        "<td align=\"left\">Terminal</td>\n" +
        "<td align=\"left\">Hoja</td>\n" +
        "<td align=\"left\">Siguientes</td>\n" +
        "</tr>"+dot+"</table>>];}";
       // System.out.println(tabla);
    }
    
    public static void agregarSiguiente(String nodos, String siguientes){
        String[] Nodos;
        String[] listaSig;
        
        Nodos = nodos.split(",");
        listaSig = siguientes.split(",");
        
        for(int i=0; i<Nodos.length ; i++){
            for(int j=0;j<Node.misSiguientes.size();j++){
                if(Node.misSiguientes.get(j).etiqueta.equals(Nodos[i])){
                    //SE AGREGAN LOS SIGUIENTES AL NODO
                    for(int s=0;s<listaSig.length;s++){
                        if(!Node.misSiguientes.get(j).siguientes.contains(listaSig[s])){
                            Node.misSiguientes.get(j).siguientes.add(listaSig[s]);
                        }
                    }
                }
            }
        }

    }

}
