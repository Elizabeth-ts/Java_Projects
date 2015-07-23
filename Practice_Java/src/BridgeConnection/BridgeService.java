/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BridgeConnection;

import DBUpdateService.UserServiceInterface;
import TestClient.UserClient;
import domain.OperationPack;
import domain.OperationPackInterface;
import domain.StepRecord;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class BridgeService {

    private final String host = "localhost";
    private final String registry = "UserServiceInterfaceImpl";
    private Thread threadObj;
    private UserServiceInterface obj;
    private final List<HandleASession> sessionPools = new ArrayList<>();

    public BridgeService() {
        initializeRMI();
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8000);

            while (true) {
                Socket socket = serverSocket.accept();
                trace(new Date() + ": Player 1 joined" + '\n');
                trace("Player 1's IP address"
                        + socket.getInetAddress().getHostAddress() + '\n');

                HandleASession task = new HandleASession(socket, sessionPools.size() + 1);
                sessionPools.add(task);
                new Thread(task).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(BridgeService.class.getName()).log(Level.SEVERE, null, ex);
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

    class HandleASession implements Runnable, OperationPackInterface {

        private final int session_ID;
        private final Socket socket;
        private ObjectInputStream fromClient;
        private ObjectOutputStream toClient;
        private OperationPack operation;

        public HandleASession(Socket socket, int session_ID) {
            this.session_ID = session_ID;
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                fromClient = new ObjectInputStream(socket.getInputStream());
                toClient = new ObjectOutputStream(socket.getOutputStream());

                while (true) {
                    operation = (OperationPack) fromClient.readObject();
                    trace("Object get!");
                    trace(operation.toString());
                    if (operation != null) {
                        switch (operation.getOperationCode()) {
                            case OPERATION_ADD_USER:
                                obj.addUser(operation.getStepRecord().getUser());
                                break;
                            case OPERATION_INSERT_NEW_STEPRECORD:
                                try {
                                    if (!obj.insertNewRecord(operation.getStepRecord())) {
                                        operation = null;
                                    }
                                } catch (RemoteException | SQLException ex) {
                                    Logger.getLogger(BridgeService.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case OPERATION_REMOVE_USER:
                                if (!obj.removeUser(operation.getStepRecord().getUser())) {
                                    operation = null;
                                }
                                break;
                            case OPERATION_SEARCH_STEPRECORD:
                                try {
                                    StepRecord temp = obj.searchStepTotalForUserWithinPeriod(operation.getStepRecord());
                                    trace("---------------------------------");
                                    trace("TEMP: " + temp.toString());
                                    trace("---------------------------------");
                                    operation.setStepRecord(temp);
                                } catch (RemoteException | SQLException ex) {
                                    Logger.getLogger(BridgeService.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            default:
                                break;
                        }
                        trace(operation.toString());
                        toClient.writeObject(operation);
                        toClient.flush();
                        trace("Object send!");
                    }
                }

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(BridgeService.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (fromClient != null) {
                        fromClient.close();
                    }
                    if (toClient != null) {
                        toClient.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(BridgeService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void trace(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        new BridgeService();
    }
}
