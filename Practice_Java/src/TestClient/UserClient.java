/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClient;

import DBUpdateService.UserServiceInterface;
import domain.StepRecord;
import domain.User;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple Client Testing Application
 *
 * @author Arvin
 */
public class UserClient {

    private final String host = "localhost";
    private final String registry = "UserServiceInterfaceImpl";
    private UserServiceInterface obj;

    public UserClient() {
        ArrayList<User> list = new ArrayList<>();
        try {
            initializeRMI();
            Timestamp start = new Timestamp(115, 6, 15, 20, 40, 0, 0);
            Timestamp end = new Timestamp(115, 6, 15, 20, 45, 0, 0);
            User user = new User(2, "Test", 15, 22.5, 32.7);

            StepRecord step = new StepRecord(200, user, start);

            if (obj.insertNewRecord(step)) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }

            step = new StepRecord(user, start, end);
            step = obj.searchStepTotalForUserWithinPeriod(step);
            System.out.println(step);
            list = obj.getAllUser();
            System.out.println(list);

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
