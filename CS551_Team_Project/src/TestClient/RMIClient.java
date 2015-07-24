/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClient;

import DataBase_Updata_Service.UserServiceInterface;
import domain.StepRecord;
import domain.UserPackage;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        UserPackage user = new UserPackage("Arvin", "123456789", step, null);
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("3");
        stack.push("5");
        stack.push("6");
        stack.push("5123123");
        stack.push("123456789");

        try {
            //obj.addUser(user);
            Stack<String> newStack = obj.searchContactList(stack);
            System.out.println(newStack);
        } catch (RemoteException ex) {
            Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
        }

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
