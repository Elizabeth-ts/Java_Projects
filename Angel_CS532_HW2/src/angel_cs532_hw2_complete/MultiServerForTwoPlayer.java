/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_hw2_complete;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class MultiServerForTwoPlayer implements GameDataConstants {

    private final List<HandleASession> sessionPools = new ArrayList<>();

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

                HandleASession task = new HandleASession(socket1, socket2, sessionPools.size() + 1);
                sessionPools.add(task);
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

        private final Socket socket1, socket2;
        private ObjectInputStream fromPlayer1, fromPlayer2;
        private ObjectOutputStream toPlayer1, toPlayer2;
        private final int session_ID;

        /**
         * Construct a thread
         *
         * @param socket1
         * @param socket2
         */
        public HandleASession(Socket socket1, Socket socket2, int session_ID) {
            this.socket1 = socket1;
            this.socket2 = socket2;
            this.session_ID = session_ID;
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

                // Signal two player that complete the matching
                toPlayer1.writeObject(true);
                toPlayer2.writeObject(true);

                while (true) {

                    // Wait the signal from two clients
                    Boolean b1 = (Boolean) fromPlayer1.readObject();
                    trace("                                                          Player1 Signal Receive: " + b1.toString());
                    Boolean b2 = (Boolean) fromPlayer2.readObject();
                    trace("                                                          Player2 Signal Receive: " + b2.toString());

                    // Signal the client to start the game
                    toPlayer1.writeObject(false);
                    toPlayer2.writeObject(false);

                    // Retrieve GameData from two players
                    GameData player1 = (GameData) fromPlayer1.readObject();
                    trace("                                                          Player1 Data Receive: " + player1.toString());
                    GameData player2 = (GameData) fromPlayer2.readObject();
                    trace("                                                          Player2 Data Receive: " + player2.toString());
                    int result = win(player1, player2);
                    player1.setOpponent(player2.getPlayer());

                    player2.setOpponent(player1.getPlayer());
                    if (result == PLAYER1_WON) {
                        player1.setResult(PLAYER1_WON);
                        player2.setResult(PLAYER2_WON);
                    } else if (result == PLAYER2_WON) {
                        player1.setResult(PLAYER2_WON);
                        player2.setResult(PLAYER1_WON);
                    } else {
                        player1.setResult(DRAW);
                        player2.setResult(DRAW);
                    }

                    trace("                                                          Result: " + result);
                    toPlayer1.writeObject(player1);
                    toPlayer1.flush();
                    toPlayer2.writeObject(player2);
                    toPlayer2.flush();
                }

            } catch (IOException | ClassNotFoundException ex) {
                
                ex.printStackTrace();
            }
        }

        // Determine who wins
        private Integer win(GameData player1, GameData player2) {
            int one = player1.getPlayer();
            int two = player2.getPlayer();
            int result = two - one;
            if (one == AUTO_LOSE && two != AUTO_LOSE) {
                return PLAYER2_WON;
            }
            if (two == AUTO_LOSE && two != AUTO_LOSE) {
                return PLAYER1_WON;
            }
            if (one == AUTO_LOSE && two == AUTO_LOSE) {
                return DRAW;
            }
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
