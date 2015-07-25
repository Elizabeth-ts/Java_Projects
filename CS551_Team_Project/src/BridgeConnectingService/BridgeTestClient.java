/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BridgeConnectingService;

import domain.ImageInBytes;
import domain.OperationCode;
import domain.OperationPackage;
import domain.Post;
import domain.StepRecord;
import domain.UserPackage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Arvin
 */
public class BridgeTestClient implements OperationCode {
    
    private final String host = "localhost";
    private final int port = 8000;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private OperationPackage operation;
    
    public BridgeTestClient() {
        Timestamp start = new Timestamp(115, 6, 15, 18, 40, 0, 0);
        Timestamp end = new Timestamp(115, 6, 15, 21, 45, 0, 0);
        StepRecord step = new StepRecord(200, start, end);
        BufferedImage img = null;
        byte[] bytes = null;
        UserPackage user = new UserPackage("Arvin", "2", step, null);
        operation = new OperationPackage(OPERATION_GET_POST, user, null, null);
        Stack<String> contactList = new Stack<>();
        contactList.push("1");
        contactList.push("3");
        contactList.push("5");
        contactList.push("7");
        contactList.push("9");
        contactList.push("11");
        operation.setContactList(contactList);
        
        try {
            img = ImageIO.read(new File("StarCraft-II-Loading-Screens-HD-Wallpapers_012.jpg"));
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            
            bytes = baos.toByteArray();
            baos.close();
            
            ImageInBytes image = new ImageInBytes("StarCraft-II-Loading-Screens-HD-Wallpapers_012.jpg",
                    "jpg", Long.valueOf(bytes.length), bytes);
            Post post = new Post("Test Message", image);
            operation.getUserPackage().setPost(post);
            
            Socket socket = new Socket(host, 8000);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
            
            toServer.writeObject(operation);
            toServer.flush();
            operation = (OperationPackage) fromServer.readObject();
            trace(operation.toString());
            
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(BridgeTestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void trace(String s) {
        System.out.println(s);
    }
    
    public static void main(String[] args) {
        new BridgeTestClient();
    }
}
