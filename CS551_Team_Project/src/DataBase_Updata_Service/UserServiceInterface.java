/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase_Updata_Service;

import domain.OperationPackage;
import domain.Post;
import domain.UserPackage;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Stack;

/**
 *
 * @author Arvin
 */
public interface UserServiceInterface extends Remote {

    /**
     * Add new User to the DB
     *
     * @throws java.rmi.RemoteException
     */
    public OperationPackage addUser(OperationPackage operation) throws RemoteException;
    public OperationPackage searchUser(OperationPackage operation) throws RemoteException ;
    public OperationPackage searchAddUser(OperationPackage operation) throws RemoteException ;
    /**
     * Remove User by giving an User object
     *
     * @param userToBeRemove
     * @return remove success or not
     * @throws java.rmi.RemoteException
     */
    public OperationPackage removeUser(OperationPackage operation) throws RemoteException;

    /**
     *
     * @param step
     * @return success or not
     * @throws RemoteException
     */
    public OperationPackage insertNewRecord(OperationPackage operation) throws RemoteException;

    /**
     *
     * @param step
     * @return StepRecord object contains new total amount of step count inside
     * @throws RemoteException
     */
    public OperationPackage searchStepTotalForUserWithinPeriod(OperationPackage operation) throws RemoteException;

    /**
     *
     * @param contactList
     * @return stack of phone number indicate who is also using the application
     */
    public OperationPackage searchContactList(OperationPackage operation) throws RemoteException;

    /**
     *
     * @param user
     * @return
     */
    public OperationPackage addPost(OperationPackage operation) throws RemoteException;

    /**
     *
     * @param user
     * @return
     */
    public OperationPackage getPost(OperationPackage operation) throws RemoteException;

}
