/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClient;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arvin
 */
public class PostClient {

    public PostClient() {
        Socket socket = null;
        BufferedImage img;
        try {
            img = ImageIO.read(new File("StarCraft-II-Loading-Screens-HD-Wallpapers_012.jpg"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(img, "jpg", baos);
            baos.flush();

            byte[] bytes = baos.toByteArray();
            baos.close();

            MyImage myImage = new MyImage("test.jpg", bytes.length, bytes);

            socket = new Socket("localhost", 8000);
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            toServer.writeObject(myImage);
            toServer.flush();

        } catch (IOException ex) {
            Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new PostClient();
    }
}
