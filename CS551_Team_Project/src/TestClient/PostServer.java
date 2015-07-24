/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClient;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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
public class PostServer extends JFrame {

    public PostServer() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedImage bImage = null;
        MyImage myImage;

        try {
            serverSocket = new ServerSocket(8000);
            socket = serverSocket.accept();

            ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
            myImage = (MyImage) fromClient.readObject();

            InputStream is = new ByteArrayInputStream(myImage.getBytes());
            bImage = ImageIO.read(is);
            File f = new File("C:" + File.separator + "imageTest" + File.separator + "a.jpg");
            f.getParentFile().mkdirs();
            f.createNewFile();
            ImageIO.write(bImage, "jpg", f);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(PostServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        setSize(500, 300);
        setTitle("Image Server");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JLabel(new ImageIcon(bImage)));

    }

    public static void main(String[] args) {
        new PostServer();
    }
}
