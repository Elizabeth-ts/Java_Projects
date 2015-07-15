/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_midterm_Q1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class ClientToSendToDoList {

    private final String host = "localhost";
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;

    public ClientToSendToDoList() {
        try {
            Socket socket = new Socket(host, 8000);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());

            while (true) {
                trace((String) fromServer.readObject());
                String task = (new Scanner(System.in)).nextLine();
                if (task.isEmpty()) {
                    break;
                }
                toServer.writeObject(task.trim());
                trace((fromServer.readObject()).toString());
            }

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientToSendToDoList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void trace(String s) {
        System.out.println(s);
    }

    public static void main(final String[] args) {
        new ClientToSendToDoList();
    }
}
