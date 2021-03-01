/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Clases;

import java.util.Stack;

/**
 *
 * @author Gerson
 */
public class MyStack {
    Stack pila = new Stack();

    public Stack getPila() {
        return pila;
    }
    public void InsertInStack(Element el){
        pila.push(el);
    }
    
    public String getElements(){
        String res="";
        Element character;
        while(pila.empty()==false){
            character = (Element)pila.pop();
            res+=" "+character.valor;
        }
        return res;
    }
    
    public void EmptyStack(){
        while(pila.empty()==false){
            pila.pop();
        }
    }

}
