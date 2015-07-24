/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase_Updata_Service;

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
     * @param newUser
     * @throws java.rmi.RemoteException
     */
    public boolean addUser(UserPackage newUser) throws RemoteException;

    /**
     * Remove User by giving an User object
     *
     * @param userToBeRemove
     * @return remove success or not
     * @throws java.rmi.RemoteException
     */
    public boolean removeUser(UserPackage userToBeRemove) throws RemoteException;

    /**
     *
     * @param step
     * @return success or not
     * @throws RemoteException
     */
    public boolean insertNewRecord(UserPackage user) throws RemoteException;

    /**
     *
     * @param step
     * @return StepRecord object contains new total amount of step count inside
     * @throws RemoteException
     */
    public UserPackage searchStepTotalForUserWithinPeriod(UserPackage user) throws RemoteException;

    /**
     *
     * @param contactList
     * @return stack of phone number indicate who is also using the application
     */
    public Stack<String> searchContactList(Stack<String> contactList) throws RemoteException;

    /**
     *
     * @param user
     * @return
     */
    public boolean addPost(UserPackage user) throws RemoteException;

    /**
     *
     * @param user
     * @return
     */
    public Stack<Post> getPost(UserPackage user) throws RemoteException;

}
