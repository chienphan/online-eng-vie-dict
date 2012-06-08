/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedict_client;

/**
 *
 * @author Administrator
 */
public class myString {
    public static String[] split(String input){
        String[] result = new String[100];
        String temp = "";
        String eol = "\n";
        int count = 0;
        char[] mychar = input.toCharArray();
        for(int i = 0; i < mychar.length; i++){
            if(mychar[i] == ','){
                count++;
                temp = ",";
                result[count] = temp;
                temp = "";
                count++;
            }
            else if(mychar[i] == '.'){
                count++;
                temp = ".";
                result[count] = temp;
                temp = "";
                count++;
            }
            else if(mychar[i] == '\n'){
                count++;
                temp = "\n";
                result[count] = temp;
                temp = "";
                count++;
            }
            else{
                temp += mychar[i];
                result[count] = temp;
                
                //temp = "";
            }
        }
//        for (int i = 0; i < result.length; i++) {
//            if(result[i].equals(eol)){
//                System.out.println('\n');
//            }
//            System.out.println("result: " + result[i].toString());
//        }
        
        return result;
    }
    
//    public static void main(String[] args){
//        String eol = "\n";
//        if(eol == "\n"){
//            System.out.print("ok");
//        }
//    }
}
