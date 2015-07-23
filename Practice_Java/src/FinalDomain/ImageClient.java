/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalDomain;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arvin
 */
public class ImageClient {

    public ImageClient() {

        Socket socket = null;
        BufferedImage img;
        try {
            socket = new Socket("localhost", 8000);
            img = ImageIO.read(new File("14417copyFrom14610.JPG"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(img, "JPG", baos);
            baos.flush();

            byte[] bytes = baos.toByteArray();
            baos.close();

            System.out.println("Sending image to server. ");

            OutputStream out = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);

            dos.writeInt(bytes.length);
            dos.write(bytes, 0, bytes.length);

            dos.close();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(ImageClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ImageClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        new ImageClient();
    }
}
