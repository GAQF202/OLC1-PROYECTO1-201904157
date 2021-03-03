/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Clases;

import java.util.*;
import App.Clases.Node;

/**
 *
 * @author Gerson
 */
public class Lexemes_found {
    public Lexemes_found(){}
    public int idDer;
    public int idIzq;
    public int idAct;
    public int idAux;
    public List<Set> Sets = new ArrayList<Set>();
    
    public void AddToList(Set conjunto){
        Sets.add(conjunto);
    }
    
    
    public void ConvertToInfix(Stack pila){
        String dotCode="";
        Element character;
        int count = 1;
        String actual="";
        
        String derecho="";
        String izquierdo="";
        String aux="";


        
        while(pila.empty()==false){
            character = (Element)pila.pop();
            character.id = count++;
            if(character.nombre.equals("conjunto")||character.nombre.equals("cadena")){

                if (derecho.equals("") && izquierdo.equals("")){
                    derecho = character.valor;
                    actual = derecho;
                    idDer = character.id;
                    idAct = idDer;
                }else if(izquierdo.equals("") && !derecho.equals("")){
                    izquierdo = character.valor;
                    actual=izquierdo;
                    idIzq = character.id;
                    idAct = idIzq;
                }else if(aux.equals("") && !izquierdo.equals("") && !derecho.equals("")){
                    aux = derecho;
                    idAux = idDer;
                    derecho = izquierdo;
                    izquierdo = character.valor;
                    actual = izquierdo;
                    idDer = idIzq;
                    idIzq = character.id;
                    idAct = idIzq;
                }
            }else if(character.valor.equals("|")||character.valor.equals(".")){

                if(!aux.equals("") && izquierdo.equals("") ){
                    derecho = derecho +character.valor+ aux;
                    actual = derecho;
                    idDer = character.id;
                    idAct = idDer;
                    idAux = 0;
                    aux = "";
                }else{
                    derecho = izquierdo + character.valor + derecho;
                    izquierdo = "";
                    actual = derecho;
                    idIzq = 0;
                    idDer = character.id;
                    idAct = idDer;
                }
            }else if(character.valor.equals("*")||character.valor.equals("+")||character.valor.equals("?")){
 
                if(izquierdo.equals(actual)){
                    izquierdo = "("+izquierdo+")" + character.valor;
                    actual = izquierdo;
                    idIzq = character.id;
                    idAct = idIzq;
                    
                }else if(derecho.equals(actual)){
                    derecho = "("+derecho+")" + character.valor;
                    actual = derecho;
                    idDer = character.id;
                    idAct = idDer;
                }
            }
            /*System.out.println(Integer.toString(nodo.hizq) +"  "+ Integer.toString(nodo.hder)+" "
            +Integer.toString(nodo.id));*/
        }
        System.out.println(dotCode);
        System.out.println(derecho);

    }
}
