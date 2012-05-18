/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedict_client;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;
/**
 *
 * @author Administrator
 */
public class FileManager{
    private String filename;
    private File file;

    public FileManager(String filename){
        this.filename = filename;
        this.file = new File(filename);
    }
    
    public FileManager() {
        this.filename = "data.txt";
        this.file = new File(this.filename);
    }
    
    public Vector<String> readLogFile() throws FileNotFoundException, IOException{
        boolean exist = this.file.exists();
        Vector<String> result = new Vector<String>();
        if(exist){
            FileReader instream = new FileReader(this.filename);
            BufferedReader in = new BufferedReader(instream);
            
            String temp = in.readLine();
            if(!(temp == null)){
                String[] data = temp.split("%");
                for(int i = 0; i < data.length; i++){
                    result.add(data[i]);
                }
                return result;
            }            
            else{
                result.add("null");
                return result;
            }
        }
        else{
            result.add("null");
            return result;
        }
    }
    
    public void writeLogFile(Vector<String> data) throws IOException{
        //boolean exist = this.file.exists();
        
        FileWriter outstream = new FileWriter(this.filename);
        BufferedWriter out = new BufferedWriter(outstream);
        for(int i = 0; i < data.size(); i++){
            out.write(data.get(i).toString() + "%");
        }
        out.close();
        
    }
    
    public void writeCSVFile(Vector<String> data) throws IOException{
        FileWriter outstream = new FileWriter(this.filename);
        BufferedWriter out = new BufferedWriter(outstream);
        Data serverData = new Data();
        
        for(int i = 0; i < data.size(); i++){
            serverData.sendText(data.get(i));
            String text = serverData.receiveText().toString();
            out.write(data.get(i).toString() + "," + text + "\n");
        }
        out.close();
    }
    
}
