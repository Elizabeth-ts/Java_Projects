/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BridgeConnectingService;

import DataBase_Updata_Service.UserServiceInterface;
import domain.OperationCode;
import domain.OperationPackage;
import domain.Post;
import domain.UserPackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
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
    private UserServiceInterface remoteObject;
    private final List<HandleASession> sessionPools = new ArrayList<>();

    public BridgeService() {
        initializeRMI();
        try {
            ServerSocket serverSocket = new ServerSocket(8000);

            while (true) {
                Socket socket = serverSocket.accept();
                trace(new Date() + ": New user joined" + '\n');
                trace("User's IP address"
                        + socket.getInetAddress().getHostAddress() + '\n');
                HandleASession task = new HandleASession(sessionPools.size() + 1, socket);
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
            remoteObject = (UserServiceInterface) reg.lookup(registry);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(BridgeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class HandleASession implements Runnable, OperationCode {

        private final int session_ID;
        private final Socket socket;
        private ObjectInputStream fromClient;
        private ObjectOutputStream toClient;
        private OperationPackage operation;

        public HandleASession(int session_ID, Socket socket) {
            this.session_ID = session_ID;
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                fromClient = new ObjectInputStream(socket.getInputStream());
                toClient = new ObjectOutputStream(socket.getOutputStream());
                while (true) {
                    operation = (OperationPackage) fromClient.readObject();
                    trace("Object get from Client!"+operation.getOperationCode());
                    if (operation == null) {
                        return;
                    }
                    switch (operation.getOperationCode()) {
                        case OPERATION_ADD_USER:
                            trace("Add User Start!");
                            operation = remoteObject.addUser(operation);
                            trace("Add User Done!  " + operation.isExecute() + operation.getUserPackage());
                            break;
                        case OPERATION_REMOVE_USER:
                            trace("Remove User Start!");
                            operation = remoteObject.removeUser(operation);
                            trace("Remove User Done!  " + operation.isExecute());
                            break;
                        case OPERATION_INSERT_NEW_STEPRECORD:
                            trace("Insert StepRecord Start!");
                            operation = remoteObject.insertNewRecord(operation);
                            trace("Insert StepRecord Done!  " + operation.isExecute() + "    " + operation.getUserPackage().getStepRecord());
                            break;
                        case OPERATION_SEARCH_STEPRECORD:
                            trace("Search Step Record Start!");
                            operation = remoteObject.searchStepTotalForUserWithinPeriod(operation);
                            System.out.println(operation.getUserPackage().getStepRecord().getStepCount());
                            trace("Search Step Record Done!  " + operation.isExecute());
                            break;
                        case OPERATION_SEARCH_CONTACT_LIST:
                            trace("Search contact list Start!");
                            operation = remoteObject.searchContactList(operation);
                            trace("Search contact list done!  " + operation.isExecute() + "    " + operation.getContactList());
                            break;
                        case OPERATION_ADD_POST:
                            trace("Add Post Start!");
                            operation = remoteObject.addPost(operation);
                            trace("Add Post Done!  " + operation.isExecute() + "     " + operation.toString());
                            break;
                        case OPERATION_GET_POST:
                            trace("Get Post Start!");
                            operation = remoteObject.getPost(operation);
                            trace("Get Post Done!  " + operation.isExecute() + "     " + operation.getPostList());
                            break;
                        case OPERATION_SEARCH_USER:
                            trace("Get Search User Start!");
                            operation = remoteObject.searchUser(operation);
                            trace("Get Search User Done!  " + operation.isExecute() + "     " + operation.getUserPackage().getUserName());
                            break;
                        case OPERATION_SEARCH_ADD_USER:
                            trace("Get Search & add User Start!");
                            operation = remoteObject.searchAddUser(operation);
                            trace("Get Search & add User Done!  " + operation.isExecute() + "     " + operation.getUserPackage().getUserId());
                            break;
                        default:
                            break;
                    }
                    toClient.writeObject(operation);
                    toClient.flush();
                    trace("Object send!");
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
                    //Logger.getLogger(BridgeService.class.getName()).log(Level.SEVERE, null, ex);
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
