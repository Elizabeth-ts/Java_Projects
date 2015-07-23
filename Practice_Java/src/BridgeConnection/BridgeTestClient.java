/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BridgeConnection;

import domain.OperationPack;
import domain.OperationPackInterface;
import domain.StepRecord;
import domain.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class BridgeTestClient implements OperationPackInterface {
    
    private final String host = "localhost";
    private final int port = 8000;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;
    private OperationPack operation;
    
    public BridgeTestClient() {
        User user = new User(5, "Arvin Mai", 35, 66, 99);
        Timestamp start = new Timestamp(115, 6, 15, 20, 40, 0, 0);
        Timestamp end = new Timestamp(115, 6, 15, 20, 45, 0, 0);
        StepRecord step = new StepRecord(0, user, start, end);
        operation = new OperationPack(step, OPERATION_SEARCH_STEPRECORD);
        try {
            Socket socket = new Socket(host, port);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromServer = new ObjectInputStream(socket.getInputStream());
            
            toServer.writeObject(operation);
            operation = (OperationPack) fromServer.readObject();
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
