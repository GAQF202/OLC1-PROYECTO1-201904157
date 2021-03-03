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
    public Node hizq;
    public Node hder;
    public String valor;
    public int id;
    
    //VARIABLES PARA RECORRER EL ARBOL
    public String anulabilidad;
    public String anterior;
    public String siguiente;
    public int enumerador;
    public int ultimo;

    public int getUltimo() {
        return ultimo;
    }

    public void setUltimo(int ultimo) {
        this.ultimo = ultimo;
    }
    
    public Node(Node hizq, Node hder, String valor, int id, String anulabilidad, String anterior, String siguiente,int enumerador){
        this.hizq=hizq;
        this.hder=hder;
        this.valor=valor;
        this.id=id;
        this.anulabilidad = anulabilidad;
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.enumerador=enumerador;
    }
    
      /* public String getCodigoInterno(){
        String etiqueta="";
        
        if(anterior.equals("")&&siguiente.equals("")&&hizq!=null){
            anterior = "jeje";
            siguiente = "equijde";
            
            if(hizq!=null && hder==null){
                anterior = hizq.anterior + "," + hder.anterior;
                siguiente = hizq.siguiente + "," + hder.siguiente;
                etiqueta += hizq.getCodigoInterno()+ " " + anterior + " " + siguiente+" "+id;
            }
            if(hder!=null){
                etiqueta += hder.getCodigoInterno()+ " " + anterior + " " + siguiente+" "+id;
            }
        }
        

        return etiqueta;
    }*/
    
    public static void recorrerArbol(Node actual){
        if(actual.siguiente.equals("") && actual.anterior.equals("")){
            if(actual.valor.equals(".")||actual.valor.equals("|")){
                recorrerArbol(actual.hder);
                recorrerArbol(actual.hizq);
                
                if(actual.valor.equals("|")){
                    actual.valor ="\\"+actual.valor;
                    if(actual.hizq.anulabilidad.equals("N")&& actual.hder.anulabilidad.equals("N")){
                        actual.anulabilidad="N";
                        actual.anterior= actual.hizq.anterior + "," + actual.hder.anterior;
                        actual.siguiente= actual.hizq.siguiente + "," + actual.hder.siguiente;
                    }else{
                        actual.anterior= actual.hizq.anterior + "," + actual.hder.anterior;
                        actual.siguiente=actual.hizq.siguiente + "," + actual.hder.siguiente;
                    }
                }else if(actual.valor.equals(".")){
 
                    if(actual.hizq.anulabilidad.equals("A") && actual.hder.anulabilidad.equals("A")){
                        actual.anulabilidad="A";
                        actual.anterior= actual.hizq.anterior + "," + actual.hder.anterior;
                        actual.siguiente = actual.hizq.siguiente + "," + actual.hder.siguiente;
                    }else{
                        if(actual.hizq.anulabilidad.equals("A")){
                            actual.anterior = actual.hizq.anterior + "," + actual.hder.anterior;
                        }
                        else{
                            actual.anterior = actual.hizq.anterior;
                        }   
                        if(actual.hder.anulabilidad.equals("A")){
                            actual.siguiente= actual.hizq.siguiente + "," + actual.hder.siguiente;
                        }else{
                            actual.siguiente = actual.hder.siguiente;
                        }
                    }
                    
                }
                
            }else{
                recorrerArbol(actual.hizq);
                actual.anterior=actual.hizq.anterior;
                actual.siguiente=actual.hizq.siguiente;
                
            }
        }
    }
    
    
    public String getCodigoInterno(){
        String etiqueta="";
        
        if(hizq==null && hder==null){
            etiqueta = "nodo"+id+"[label=\""+anterior+"|{"+anulabilidad+"|"+valor+"|id: "+enumerador+"}|{"+ siguiente +"}\"];\n";
        }else{
            etiqueta = "nodo"+id+"[label=\""+anterior+"|{"+anulabilidad+"|"+valor+"|id: "+enumerador+"}|{"+ siguiente +"}\"];\n";
        }
        //CREACION DE HOJA CON HIJOS
        if(hizq !=null){
            etiqueta+=hizq.getCodigoInterno()+"nodo"+id+"->nodo"+hizq.id+";\n";
        }
        if(hder!=null){
            etiqueta+=hder.getCodigoInterno()+"nodo"+id+"->nodo"+hder.id+";\n";
        }
        return etiqueta;
    }
 
}
