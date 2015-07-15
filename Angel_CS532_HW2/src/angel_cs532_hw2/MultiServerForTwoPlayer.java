/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_hw2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class MultiServerForTwoPlayer implements DataObjectConstants {

    public MultiServerForTwoPlayer() {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);

            // Ready to create a session for every two players
            while (true) {
                trace("\nWaiting for player one...");
                Socket socket1 = serverSocket.accept();
                trace(new Date() + ": Player 1 joined" + '\n');
                trace("Player 1's IP address"
                        + socket1.getInetAddress().getHostAddress() + '\n');
                trace("\nWaiting for player two...");
                Socket socket2 = serverSocket.accept();
                trace(new Date() + ": Player 2 joined" + '\n');
                trace("Player 2's IP address"
                        + socket2.getInetAddress().getHostAddress() + '\n');

                HandleASession task = new HandleASession(socket1, socket2);
                new Thread(task).start();
                trace("Thread Start!!");
            }

        } catch (IOException ex) {
            Logger.getLogger(MultiServerForTwoPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Inner Class
    // Define the thread class for handling a new session for two players
    class HandleASession implements Runnable {

        private Socket socket1, socket2;
        private ObjectInputStream fromPlayer1, fromPlayer2;
        private ObjectOutputStream toPlayer1, toPlayer2;

        /**
         * Construct a thread
         *
         * @param socket1
         * @param socket2
         */
        public HandleASession(Socket socket1, Socket socket2) {
            this.socket1 = socket1;
            this.socket2 = socket2;
        }

        /**
         * Implement the run() method for the thread
         */
        @Override
        public void run() {
            try {
                // Create object I/O stream
                fromPlayer1 = new ObjectInputStream(socket1.getInputStream());
                fromPlayer2 = new ObjectInputStream(socket2.getInputStream());

                toPlayer1 = new ObjectOutputStream(socket1.getOutputStream());
                toPlayer2 = new ObjectOutputStream(socket2.getOutputStream());

                while (true) {
                    DataObject player1 = (DataObject) fromPlayer1.readObject();
                    trace("                                                          Player1 Data Receive");
                    DataObject player2 = (DataObject) fromPlayer2.readObject();
                    trace("                                                          Player2 Data Receive");
                    Integer result = win(player1, player2);
                    trace("                                                          Result: " + result);
                    toPlayer1.writeObject(result);
                    toPlayer2.writeObject(result);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // Determine who wins
        private Integer win(DataObject player1, DataObject player2) {
            int one = player1.getData();
            int two = player2.getData();
            int result = two - one;
            if (one == two) {
                return DRAW;
            }
            if (result == 1 || result == -2) {
                return PLAYER2_WON;
            }
            return PLAYER1_WON;
        }

        //private helping function
        private void trace(String s) {
            System.out.println(s);
        }
    }

    //private helping function
    private void trace(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        new MultiServerForTwoPlayer();
    }
}
