/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBUpdateService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class RegisterWithRMIServer {

    public static void main(String[] args) {
        try {
            UserServiceInterface obj
                    = new UserServiceInterfaceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("UserServiceInterfaceImpl", obj);
            System.out.println("User server " + obj + " registered");
            
        } catch (RemoteException ex) {
            Logger.getLogger(RegisterWithRMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
