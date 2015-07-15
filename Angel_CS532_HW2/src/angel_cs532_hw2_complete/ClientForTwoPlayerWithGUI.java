/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_hw2_complete;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author Arvin
 */
public class ClientForTwoPlayerWithGUI extends JPanel implements GameDataConstants {

    // Field members
    private final JTextArea textArea;
    // Button for Start the Game
    private final JButton jbtStart;
    // Buttons for symbols
    private final JButton jbtRock, jbtPaper, jbtScissor;
    // Panels contain ImageIcon
    private final ImagePanel panelPlayer1, panelPlayer2;
    // Image Icon for symbols
    private final ImageIcon imgRock, imgPaper, imgScissor;
    // Server Host Name
    private final String host = "localhost";
    // Main Frame contains all components
    private static JFrame frame;
    // Data member stores records
    private int winCount = 0, loseCount = 0, drawCount = 0;
    // button click signal
    private boolean buttonClick = false;
    private Thread threadObject;
    private boolean paused;
    private GameData gameData;
    private ObjectOutput toServer;
    private ObjectInput fromServer;

    /**
     * Constructor
     */
    public ClientForTwoPlayerWithGUI() {
        // Create components
        paused = true;
        textArea = new JTextArea(5, 30);
        jbtStart = new JButton("Begin");
        jbtRock = new JButton("Rock");
        jbtPaper = new JButton("Paper");
        jbtScissor = new JButton("Scissor");
        imgRock = new ImageIcon(STRING_IMAGE_ROCK);
        imgPaper = new ImageIcon(STRING_IMAGE_PAPER);
        imgScissor = new ImageIcon(STRING_IMAGE_SCISSOR);
        panelPlayer1 = new ImagePanel(STRING_IMAGE_PAPER);
        panelPlayer2 = new ImagePanel(STRING_IMAGE_PAPER);

        initComponents();
    }

    /**
     * Initializes Components
     */
    public void initComponents() {

        JPanel mainPanel, subPanel, labelPanel, symbolButtonPanel;

        // Initial Panel contains two Image symbols
        mainPanel = new JPanel(new GridLayout(1, 2));
        // add components to main Panel
        mainPanel.add(panelPlayer1);
        mainPanel.add(panelPlayer2);

        // Initial Panel contains all the buttons in symbolButtonPanel and text area
        subPanel = new JPanel(new BorderLayout());
        symbolButtonPanel = new JPanel(new FlowLayout());
        symbolButtonPanel.add(jbtPaper);
        symbolButtonPanel.add(jbtScissor);
        symbolButtonPanel.add(jbtRock);
        subPanel.add(symbolButtonPanel, BorderLayout.CENTER);
        subPanel.add(jbtStart, BorderLayout.EAST);
        subPanel.add(new JScrollPane(textArea), BorderLayout.SOUTH);

        // Initial Panel contains labels
        labelPanel = new JPanel(new GridLayout(1, 2));
        labelPanel.add(new JLabel("Player", JLabel.CENTER));
        labelPanel.add(new JLabel("Opponent", JLabel.CENTER));

        // Initial "this" Panel
        setLayout(new BorderLayout());
        add(labelPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(subPanel, BorderLayout.SOUTH);

        // Construct components 
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Setup for button start
        jbtStart.addActionListener(new ButtonListener());
        jbtStart.setEnabled(false);

        // Setup for three symbol buttons
        jbtRock.addActionListener(new ButtonChoiceListener(ROCK));
        jbtRock.setEnabled(false);
        jbtPaper.addActionListener(new ButtonChoiceListener(PAPER));
        jbtPaper.setEnabled(false);
        jbtScissor.addActionListener(new ButtonChoiceListener(SCISSOR));
        jbtScissor.setEnabled(false);

        threadObject = new Thread(new HandleClient());
        threadObject.start();
    }

    class HandleClient implements Runnable {

        @Override
        public void run() {
            trace("Started");
            try {
                // Establish connection with the server
                Socket socket = new Socket(host, 8000);

                // Create an I/O stream to the server
                toServer = new ObjectOutputStream(socket.getOutputStream());
                fromServer = new ObjectInputStream(socket.getInputStream());

                // Waiting Signal from server to notify the other player found
                boolean temp = (Boolean) fromServer.readObject();

                while (true) {
                    jbtStart.setEnabled(true);
                    gameData = new GameData();
                    // Pause the thread
                    if (paused) {
                        synchronized (threadObject) {
                            try {
                                threadObject.wait();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ClientForTwoPlayerWithGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                    synchronized (threadObject) {
                        try {
                            threadObject.sleep(5000);
                        } catch (InterruptedException ex) {

                        }
                    }
                    // send data if button never been clicked
                    if (!buttonClick) {
                        sendGameData(AUTO_LOSE);
                    }

                    // Retrieve Result Object from server
                    gameData = (GameData) fromServer.readObject();
                    resetImage(panelPlayer2, gameData.getOpponent());
                    trace(gameData.toString());
                    switch (gameData.getResult()) {
                        case PLAYER1_WON:
                            winCount++;
                            textArea.setText("You Win!!!!");
                            break;
                        case PLAYER2_WON:
                            loseCount++;
                            textArea.setText("You Lose!!!!");
                            break;
                        default:
                            drawCount++;
                            textArea.setText("Draw!!!!");
                            break;
                    }
                    textArea.append("\nYou currently hold " + winCount + " WIns, and "
                            + loseCount + " Loses, and " + drawCount + " Draws.");
                    buttonClick = false;
                }

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ClientForTwoPlayerWithGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Handle the button listener to signal the server that client is ready to start the gamer
    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            jbtStart.setEnabled(false);
            jbtRock.setEnabled(true);
            jbtPaper.setEnabled(true);
            jbtScissor.setEnabled(true);
            try {
                toServer.writeObject(true);
                paused = (Boolean) fromServer.readObject();
                jbtStart.setText("Start!!!");
            } catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(ClientForTwoPlayerWithGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            synchronized (threadObject) {
                threadObject.notify();
            }
        }
    }

    // Handle the button listener to make choice of paper, rock, scissor
    class ButtonChoiceListener implements ActionListener {

        private int choice;

        public ButtonChoiceListener(final int choice) {
            this.choice = choice;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            threadObject.interrupt();
            buttonClick = true;
            sendGameData(choice);
        }
    }

    // Private helping function trace
    private void trace(String s) {
        System.out.println(s);
    }

    // Private helping function to send game data to server
    private void sendGameData(int choice) {
        resetSetting();
        try {
            toServer.writeObject(new GameData(choice));
            toServer.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientForTwoPlayerWithGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        resetImage(panelPlayer1, choice);
    }

    // Private helping function to set image to an ImagePanel and resize the frame
    private void resetImage(ImagePanel panel, int img) {
        panel.setImage(img);
        frame.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        frame.pack();
    }

    private void resetSetting() {
        jbtRock.setEnabled(false);
        jbtPaper.setEnabled(false);
        jbtScissor.setEnabled(false);
        paused = true;
    }

    // Main function for Client application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new ClientForTwoPlayerWithGUI());
                frame.pack();
                frame.setVisible(true);
                frame.setTitle("Welcome to the Game");
                frame.setLocationRelativeTo(null);
            }
        });
    }
}
