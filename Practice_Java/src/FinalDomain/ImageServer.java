/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalDomain;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Arvin
 */
public class ImageServer extends JFrame {

    public ImageServer() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedImage bImage = null;
        try {
            serverSocket = new ServerSocket(8000);
            socket = serverSocket.accept();

            InputStream in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);

            int len = dis.readInt();

            byte[] data = new byte[len];
            dis.readFully(data);
            dis.close();
            in.close();

            InputStream fromClient = new ByteArrayInputStream(data);
            bImage = ImageIO.read(fromClient);

        } catch (IOException ex) {
            Logger.getLogger(ImageServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        setSize(500, 300);
        setTitle("Image Server");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JLabel(new ImageIcon(bImage)));

    }

    public static void main(String[] args) {
        new ImageServer();
    }

}
