/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedict_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Phan Duc Chien
 */
public class Onlinedict_server {
    private static int i = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket server = new ServerSocket(7000);
        System.out.println("Server is starting");
        while(true)
        {
            i++;
            Socket socket = server.accept();
            Dict dict = new Dict(socket, i);
            dict.start();
            System.out.println("client " + i + " is starting");
        }
    }
}
