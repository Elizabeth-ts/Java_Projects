/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClient;

import DataBase_Updata_Service.UserServiceInterface;
import domain.Post;
import domain.StepRecord;
import domain.UserPackage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arvin
 */
public class RMIClient {

    private final String host = "localhost";
    private final String registry = "UserServiceInterfaceImpl";
    private UserServiceInterface obj;

    public RMIClient() {
        initializeRMI();
        Timestamp start = new Timestamp(115, 6, 15, 20, 40, 0, 0);
        Timestamp end = new Timestamp(115, 6, 15, 21, 45, 0, 0);
        StepRecord step = new StepRecord(200, start, end);
        BufferedImage img = null;
        byte[] bytes = null;
        /*
         try {
         img = ImageIO.read(new File("StarCraft-II-Loading-Screens-HD-Wallpapers_012.jpg"));

         ByteArrayOutputStream baos = new ByteArrayOutputStream();

         ImageIO.write(img, "jpg", baos);
         baos.flush();

         bytes = baos.toByteArray();
         baos.close();

         } catch (IOException ex) {
         Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
         }*/

        //ImageInBytes image = new ImageInBytes("StarCraft-II-Loading-Screens-HD-Wallpapers_012.jpg",
        //       "jpg", Long.valueOf(bytes.length), bytes);
        //Post post = new Post("Test Message", image);
        UserPackage user = new UserPackage("Arvin", "123456789", step, null);
        //user.setPost(post);
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("3");
        stack.push("5");
        stack.push("6");
        stack.push("5123123");
        stack.push("123456789");
/*
        try {
            //obj.addUser(user);
            //Stack<String> newStack = obj.searchContactList(stack);
            //obj.addPost(user);
            //System.out.println();
           // Stack<Post> postStack = obj.getPost(user);
            
            while (!postStack.isEmpty()) {
                Post newPost = postStack.pop();
                InputStream is = null;
                File newImageFile = null;
                is = new ByteArrayInputStream(newPost.getImageInBytes().getImageData());
                BufferedImage bImage = ImageIO.read(is);
                newImageFile = new File("a.jpg");
//                newImageFile.getParentFile().mkdirs();
                newImageFile.createNewFile();
                ImageIO.write(bImage, newPost.getImageInBytes().getImageFileType(), newImageFile);
            }

        } catch (IOException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    protected void initializeRMI() {
        try {
            Registry reg = LocateRegistry.getRegistry(host);
            obj = (UserServiceInterface) reg.lookup(registry);

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new RMIClient();
    }
}
