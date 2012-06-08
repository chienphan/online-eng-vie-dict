/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedict_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Phan Duc Chien
 */
public class Dict extends Thread{
    private String eng;
    private String vie;
    private Socket socket;
    //private Vector<Socket> vector;
    private int i;
    
    
    public Dict(){
        this.eng = " ";
        this.vie = " ";
        this.socket = new Socket();
        this.i = 0;
        //this.vector = new Vector<Socket>();
    }
    
    public Dict(String eng, String vie){
        this.eng = eng;
        this.vie = vie;
        this.socket = new Socket();
        this.i = 0;
        //this.vector = new Vector<Socket>();
    }
    
    public Dict(Socket socket, int i){
        this.socket = socket;
        this.i = i;
        //this.vector = vector;
    }
    
    public String getEng() {
        return eng;
    }

    public String getVie() {
        try {
            this.vie = " ";
            //create connection
            Class.forName(Driver.dbClass); 
            java.sql.Connection con = (java.sql.Connection) DriverManager.getConnection (Driver.dbString);
      
            //create queries statement and assign values
            String query = "Select eng, vie from dicttable where eng = ? order by eng";
            PreparedStatement pstm = con.prepareStatement(query);    
            pstm.setString(1, String.valueOf(eng));
            
            //execute queries
            ResultSet rs = pstm.executeQuery();

            //pstm.executeUpdate();  //if this query don't return data

            //get data return
            while (rs.next()) {
                this.setEng(rs.getString(1));
                this.setVie(rs.getString(2));
            } //end while

            //close connection
            con.close();
            return vie;
        } //end try

        catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERR : in Dict() 1");
            return null;
        }

        catch(SQLException e) {
            e.printStackTrace();
            System.out.println("ERR : in Dict() 2");
            return null;
        }
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public void setVie(String vie) {
        this.vie = vie;
    }

    @Override
    public void run(){
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            //DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while(true)
            {
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                String msg = in.readUTF();
                if(msg.equals("getdata")){
                    out.writeUTF(this.getDatalist().toString());
                    out.flush();
                }
                else{
                    this.setEng(msg);
                    System.out.println("client "+ this.i +": " + msg);
                    System.out.println(this.getVie());
                    //DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    if(this.getVie() != null){
                        out.writeUTF(msg + " :" + this.getVie());
                        out.flush();
                    }
                    else{
                        out.writeUTF("null");
                        out.flush();
                    }
                }
            }
            //socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public String getDatalist(){
        try {
            
            //create connection
            Class.forName(Driver.dbClass); 
            java.sql.Connection con = (java.sql.Connection) DriverManager.getConnection (Driver.dbString);
      
            //create queries statement and assign values
            String query = "Select eng from dicttable order by eng";
            PreparedStatement pstm = con.prepareStatement(query);   
            
            //execute queries
            ResultSet rs = pstm.executeQuery();

            //pstm.executeUpdate();  //if this query don't return data

            //get data return
            String result = "";
            while (rs.next()) {
                result = result + rs.getString(1) + "%";
                
            } //end while

            //close connection
            con.close();
            return result;
        } //end try

        catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERR : in Dict() 1");
            return null;
        }

        catch(SQLException e) {
            e.printStackTrace();
            System.out.println("ERR : in Dict() 2");
            return null;
        }
    }
    
    public void createNew(){
        try {

        //create connection
        Class.forName(Driver.dbClass); 
        java.sql.Connection con = (java.sql.Connection) DriverManager.getConnection (Driver.dbString);

        //create queries statement and assign values
        String query = "INSERT INTO dicttable (eng, vie) VALUES (?, ?)";
        PreparedStatement pstm = con.prepareStatement(query);   
        pstm.setString(1, this.eng);
        pstm.setString(2, this.vie);
        //execute queries
        //ResultSet rs = pstm.executeQuery();
        System.out.println(pstm);
        pstm.executeUpdate();  //if this query don't return data

        //close connection
        con.close();
        } //end try

        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isInDatabase(String eng){
        try {
            
            //create connection
            Class.forName(Driver.dbClass); 
            java.sql.Connection con = (java.sql.Connection) DriverManager.getConnection (Driver.dbString);
      
            //create queries statement and assign values
            String query = "Select eng from dicttable where eng = ?";
            PreparedStatement pstm = con.prepareStatement(query);   
            pstm.setString(1, eng);
            //execute queries
            ResultSet rs = pstm.executeQuery();

            //pstm.executeUpdate();  //if this query don't return data
            if(rs.next()){
                System.out.println("t "+rs.getString(1));
                con.close();
                return true;
            }
            else{
                System.out.println("f ");
                con.close();
                return false;
                
            }
           
        } //end try

        catch(ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void delete(){
        
        try {

        //create connection
        Class.forName(Driver.dbClass); 
        java.sql.Connection con = (java.sql.Connection) DriverManager.getConnection (Driver.dbString);

        //create queries statement and assign values
        String query = "DELETE FROM dicttable WHERE eng = ?";
        PreparedStatement pstm = con.prepareStatement(query);   
        pstm.setString(1, this.eng);
        //pstm.setString(2, this.vie);
        //execute queries
        //ResultSet rs = pstm.executeQuery();
        System.out.println(pstm);
        pstm.executeUpdate();  //if this query don't return data

        //close connection
        con.close();
        } //end try

        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void update(String oldStr){
        
        try {

        //create connection
        Class.forName(Driver.dbClass); 
        java.sql.Connection con = (java.sql.Connection) DriverManager.getConnection (Driver.dbString);

        //create queries statement and assign values
        String query = "UPDATE  dicttable SET  eng = ?, vie = ?  WHERE  eng = ?";
        PreparedStatement pstm = con.prepareStatement(query);   
        pstm.setString(1, this.eng);
        pstm.setString(2, this.vie);
        pstm.setString(3, oldStr);
        //execute queries
        //ResultSet rs = pstm.executeQuery();
        System.out.println(pstm);
        pstm.executeUpdate();  //if this query don't return data

        //close connection
        con.close();
        } //end try

        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        catch(SQLException e) {
            e.printStackTrace();
        }     
    }
}
