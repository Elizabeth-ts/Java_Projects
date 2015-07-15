/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClient;

import DBUpdateService.UserServiceInterface;
import domain.User;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class UserClient {

    private final String host = "localhost";
    private final String registry = "UserServiceInterfaceImpl";
    private User newUser;
    private UserServiceInterface obj;

    public UserClient() {
        ArrayList<User> list = new ArrayList<>();
        try {
            initializeRMI();
            newUser = new User("Arvin", 15, 20.5, 15.5);
            obj.addUser(newUser);
            obj.addUser(new User("Arvin2", 200, 400, 500));

            list = obj.getAllUser();
            System.out.println(list);
            obj.removeUser(newUser);

        } catch (RemoteException ex) {
            Logger.getLogger(UserClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void initializeRMI() {
        try {
            Registry reg = LocateRegistry.getRegistry(host);
            obj = (UserServiceInterface) reg.lookup(registry);

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(UserClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        UserClient client = new UserClient();
    }
}
