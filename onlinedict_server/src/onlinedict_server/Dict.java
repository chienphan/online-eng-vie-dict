/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedict_server;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Phan Duc Chien
 */
public class Dict {
    private String eng;
    private String vie;
    
    public Dict(){
        this.eng = " ";
        this.vie = " ";
    }
    
    public Dict(String eng, String vie){
        this.eng = eng;
        this.vie = vie;
    }
    
    public Dict(String eng) throws ClassNotFoundException{
        try {
            //create connection
            Class.forName(Driver.dbClass); 
            java.sql.Connection con = (java.sql.Connection) DriverManager.getConnection (Driver.dbUrl, Driver.dbUser, Driver.dbPass);
      
            //create queries statement and assign values
            String query = "Select eng, vie from dicttable where eng = ?";
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
        } //end try

        catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERR : in Dict() 1");
        }

        catch(SQLException e) {
            e.printStackTrace();
            System.out.println("ERR : in Dict() 2");
        }
    }

    public String getEng() {
        return eng;
    }

    public String getVie() {
        return vie;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public void setVie(String vie) {
        this.vie = vie;
    }
    
}
