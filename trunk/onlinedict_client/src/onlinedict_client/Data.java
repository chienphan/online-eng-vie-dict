/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinedict_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Administrator
 */
public class Data {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Data() throws UnknownHostException, IOException {
        this.socket = new Socket("localhost", 7000);
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    public void sendText(String string) throws IOException{
        this.out.writeUTF(string);
        this.out.flush();
    }
    public String receiveText() throws IOException{
        return this.in.readUTF();
    }
    
    public Data(Socket socket, DataInputStream in) {
        this.socket = socket;
        this.in = in;
    }

    public DataInputStream getIn() {
        return this.in;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }
    
    
}
