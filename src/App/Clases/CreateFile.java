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
import java.io.IOException;
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
        String line = "";
        this.GlobalPath = path;
        
        try{
        BufferedReader bf = new BufferedReader(new FileReader(path));
        //while((bfRead = bf.readLine()) != null){
            while(((line=bf.readLine())!=null)){
            
                Text += line+"\n";
            }
        //}
        
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
            fichero = new FileWriter("./ARBOLES_201904157/" + name + ".dot");
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
        digraph("./ARBOLES_201904157/" +name);
    }
    
    
    
    
    public static void CreateDigraph(String dot, String name){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(name + ".dot");
            pw = new PrintWriter(fichero);
            pw.println(dot);
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
        digraph(name);
    }
    
    
    
    
    public static void digraph(String name){
            try {

              String dotPath = "C:\\Archivos de programa\\Graphviz\\bin\\dot.exe";

              String fileInputPath = "./" + name + ".dot";
              String fileOutputPath = "./" + name + ".png";

              String tParam = "-Tpng";
              String tOParam = "-o";

              String[] cmd = new String[5];
              cmd[0] = dotPath;
              cmd[1] = tParam;
              cmd[2] = fileInputPath;
              cmd[3] = tOParam;
              cmd[4] = fileOutputPath;

              Runtime rt = Runtime.getRuntime();

              rt.exec( cmd );

            } catch (Exception ex) {
              ex.printStackTrace();
            } finally {
            }
    }
    
    
    public static void CreateJson(String name){
        String content ="";
        for(int i=0; i<Verificadores.json.size(); i++){
            content+=Verificadores.json.get(i);
        }
        
        
        try {

            FileWriter file = new FileWriter("./SALIDAS_201904157/salida"+name+".json");
            file.write("[\n"+content+"]");
            file.flush();
            file.close();
            Verificadores.json.clear();

        } catch (IOException e) {
            System.out.println("No se pudo generar el archivo JSON");
                //manejar error
        }
        
        
        
    }
    
    
        public static void CreateAfnd(String dot, String name){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("./AFND_20190157/" + name + ".dot");
            pw = new PrintWriter(fichero);
            pw.println("digraph G{");
            pw.println("rankdir=LR");
            pw.println("node[shape=circle]");
            pw.println("size=\"15\"");
            pw.println(dot);
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
        digraph("./AFND_20190157/" +name);
    }
    
}

