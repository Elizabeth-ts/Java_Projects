/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_hw2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Arvin
 */
public class MultiServer {

    private ObjectOutputStream outputToFile;
    private ObjectInputStream inputFromClient;

    public MultiServer() {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);
            trace("Server started!");

            // Create an object output stream
            outputToFile = new ObjectOutputStream(new FileOutputStream("data.dat", true));

            while (true) {
                // Listen for a new connection request
                Socket socket = serverSocket.accept();
                /*
                 // Create an input stream from the socket
                 inputFromClient = new ObjectInputStream(socket.getInputStream());

                 // Read from input
                 DataObject object = (DataObject) inputFromClient.readObject();
                 trace(object.toString());
                 // Write to the file 
                 outputToFile.writeObject(object);
                 trace("A new Data object is stored");
                 */
                HandleAClient task = new HandleAClient(socket);
                new Thread(task).start();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                inputFromClient.close();
                outputToFile.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Inner class
    // Define the thread class for handling new connection
    class HandleAClient implements Runnable {

        private Socket socket;

        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        /**
         * Run a thread
         */
        @Override
        public void run() {
            try {
                // Create data input and output streams
                ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());

                // Continuously serve the client
                while (true) {
                    // Receive from client
                    DataObject obj = (DataObject) inputFromClient.readObject();
                    trace("Object receive: " + obj.toString());
                    obj.setData(-1);

                    // Send back to the client
                    outputToClient.writeObject(obj);
                    trace("Object send");
                }

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void trace(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        new MultiServer();
    }
}
