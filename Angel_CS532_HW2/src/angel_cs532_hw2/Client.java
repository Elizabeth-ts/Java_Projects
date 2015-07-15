/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_hw2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Arvin
 */
public class Client {

    private String host = "localhost";
    private DataObject dobj;
    ObjectOutputStream toServer;
    ObjectInputStream fromServer;

    public Client() {
        try {
            // Establish connection with the server
            Socket socket = new Socket(host, 8000);

            // Create an I/O stream to the server
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());

            while (true) {
                // Read data from terminal
                dobj = new DataObject(new Scanner(System.in).nextInt());

                toServer.writeObject(dobj);
                toServer.flush();
                trace("Object send!");
                trace(((Integer) fromServer.readObject()).toString());

                /*
                 // Send to the server
                 toServer.writeObject(dobj);
                 toServer.flush();
                 trace(((DataObject) fromServer.readObject()).toString());
                 */
            }

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                toServer.close();
                //fromServer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }

    private void trace(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        new Client();
    }
}
