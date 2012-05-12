/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedict_server;

import java.util.Scanner;

/**
 *
 * @author Phan Duc Chien
 */
public class Onlinedict_server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        String myString = scanner.nextLine();
        Dict dict = new Dict(myString);
        if(dict.getVie() != null){
            System.out.println(dict.getVie().toString());
        }
        else{
            System.out.println("Khong co tu: " + myString);
        }
    }
}
