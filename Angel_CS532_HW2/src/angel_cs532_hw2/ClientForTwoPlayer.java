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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class ClientForTwoPlayer implements Runnable, DataObjectConstants {

    // I/O streams from/to server
    private ObjectInputStream fromServer;
    private ObjectOutputStream toServer;
    private final DataObject dObj = new DataObject(0);
    private final String host = "localhost";
    private Socket socket;

    public ClientForTwoPlayer() {
        try {
            // Create a socket to connect to the server
            socket = new Socket(host, 8000);
        } catch (IOException ex) {
            Logger.getLogger(ClientForTwoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        dObj.setData(new Scanner(System.in).nextInt());
        try {

            // Create a input stream to receive data from the server
            fromServer = new ObjectInputStream(socket.getInputStream());

            // Create an output stream to send data to server
            toServer = new ObjectOutputStream(socket.getOutputStream());

            toServer.writeObject(dObj);
            toServer.flush();
            trace("Object send!");
            trace(((Integer) fromServer.readObject()).toString());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientForTwoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                toServer.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientForTwoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void trace(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        new ClientForTwoPlayer();
    }
}
