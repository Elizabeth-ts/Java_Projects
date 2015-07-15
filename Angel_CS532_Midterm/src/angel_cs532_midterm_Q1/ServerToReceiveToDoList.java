/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_midterm_Q1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class ServerToReceiveToDoList {

    private final List<String> toDoList = new ArrayList<>();

    public ServerToReceiveToDoList() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            while (true) {
                Socket socket = serverSocket.accept();
                Thread t = new Thread(new HandleASession(socket));
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerToReceiveToDoList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class HandleASession implements Runnable {

        private final Socket socket;
        private ObjectOutputStream toClient;
        private ObjectInputStream fromClient;

        public HandleASession(Socket socket) {
            this.socket = socket;

        }

        @Override
        public void run() {
            try {
                toClient = new ObjectOutputStream(socket.getOutputStream());
                fromClient = new ObjectInputStream(socket.getInputStream());
                while (true) {
                    toClient.writeObject("Enter a Task: ");
                    toClient.flush();

                    String task = (String) fromClient.readObject();
                    toDoList.add(task);
                    toClient.writeObject(toDoList);
                    toClient.reset();
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ServerToReceiveToDoList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * private trace
     *
     * @param s
     */
    private void trace(String s) {
        System.out.println(s);
    }

    /**
     * Main Function
     *
     * @param args
     */
    public static void main(final String[] args) {
        new ServerToReceiveToDoList();
    }
}
