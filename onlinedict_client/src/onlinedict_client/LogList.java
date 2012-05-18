/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedict_client;

import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class LogList {
    private Vector<String> loglist;
    
    public LogList() {
        this.loglist = new Vector<String>();
    }
    
    public boolean checkElement(String string){
        int count = 0;
        if(!this.loglist.isEmpty()){
            for(int i = 0; i < this.loglist.size(); i++){
                if(string.equals(this.loglist.get(i))){
                    count += 1;
                }
            }
        }
        if(count == 0){
            System.out.println("khong co");
            return false;
        }
        else{
            System.out.println("co");
            return true;
        }
    }

    public Vector<String> getLoglist() {
        return loglist;
    }

    public void setLoglist(Vector<String> loglist) {
        this.loglist = loglist;
    }
    
    public void add(String string){
        try {
            this.loglist.add(string);
        } catch (Exception e) {
        }
    }
}
