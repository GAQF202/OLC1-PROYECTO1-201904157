/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App.Clases;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author Gerson
 */
public class CreateFile {
    String GlobalPath;
    public CreateFile(){}
   
    public void NewFile(String path){
        File file;
        
        try{
        file = new File(path);
        if(file.createNewFile()){
            System.out.println("Se ha creado el archivo");
        }
       }catch(Exception e){
           System.out.println(e);
       }
    }
    
    public boolean IsOlC(String path){
        int index = path.lastIndexOf(".");
        boolean isolc=false;
        if(path.substring(index+1).toString().equals("olc")){
            isolc = true;
        }
        return isolc;
    }
    
    public String ReadFile(String path){
        String bfRead;
        String Text = "";
        this.GlobalPath = path;
        
        try{
        BufferedReader bf = new BufferedReader(new FileReader(path));
        while((bfRead = bf.readLine()) != null){
            Text += bfRead;
        }
        
        }catch(Exception e){
            System.out.println(e.toString());
        }
        
        return Text;
    }
    
    public String GetPath(){
        return this.GlobalPath;
    }
    
    public void SaveFile(String content){
        File file;
        FileWriter w;
        BufferedWriter bf;
        PrintWriter wr;
        
        try{
            file =  new File(this.GlobalPath);
            w = new FileWriter(file);
            bf = new BufferedWriter(w);
            wr = new PrintWriter(bf);
            wr.write(content);
            wr.close();
            bf.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public void CreateTree(Node dot, String name){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("./" + name + ".dot");
            pw = new PrintWriter(fichero);
            pw.println("digraph G{");
            pw.println("rankdir=UD");
            pw.println("node[shape=record]");
            pw.println("concentrate=true");
            pw.println(dot.getCodigoInterno());
            pw.println("}");
        } catch (Exception e) {
            System.out.println("error, no se realizo el archivo");
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
}

