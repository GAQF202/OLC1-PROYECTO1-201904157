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
public class Node {
    String valor;
    int hizq;
    int hder;
    int id;
    public Node(int hizq, int hder, String valor, int id){
        this.hizq=hizq;
        this.hder=hder;
        this.valor=valor;
        this.id=id;
    }
    
    public String getCodigoInterno(){
        String etiqueta="";
        if(hizq==0 && hder==0){
            etiqueta = "nodo"+id+"[label=\""+valor+"\"];\n";
        }
        else {
            etiqueta = "nodo" + id + " [ label =\"" + valor + "\"];\n";
        }
        if(hizq !=0){
            etiqueta+="nodo"+Integer.toString(id)+"->nodo"+Integer.toString(hizq)+";\n";
        }
        if(hder!=0){
            etiqueta+="nodo"+Integer.toString(id)+"->nodo"+Integer.toString(hder)+";\n";
        }
        return etiqueta;
    }
}
