/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBUpdateService;

import domain.StepRecord;
import domain.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Stack;

/**
 *
 * @author Arvin
 */
public interface UserServiceInterface extends Remote {

    public static final int OPERATION_ADD_USER = 0;
    public static final int OPERATION_REMOVE_USER = 1;
    public static final int OPERATION_INSERT_NEW_STEPRECORD = 2;
    public static final int OPERATION_SEARCH_STEPRECORD = 3;
    public static final int OPERATION_SEARCH_CONTACT_LIST = 4;
    public static final int OPERATION_ADD_POST = 5;
    public static final int OPERATION_RETRIEVE_POST = 6;

    /**
     * Add new User to the DB
     *
     * @param newUser
     * @throws java.rmi.RemoteException
     */
    public void addUser(User newUser) throws RemoteException;

    /**
     * Remove User by giving an User object
     *
     * @param userToBeRemove
     * @return remove success or not
     * @throws java.rmi.RemoteException
     */
    public boolean removeUser(User userToBeRemove) throws RemoteException;

    /**
     *
     * @param step
     * @return success or not
     * @throws RemoteException
     */
    public boolean insertNewRecord(StepRecord step) throws RemoteException, SQLException;

    /**
     *
     * @param step
     * @return StepRecord object contains new total amount of step count inside
     * @throws RemoteException
     */
    public StepRecord searchStepTotalForUserWithinPeriod(StepRecord step) throws RemoteException, SQLException;

    /**
     *
     * @return stack of phone number indicate who is also using the application
     */
    public Stack<String> searchContactList(Stack<String> contactList);
    
    public boolean addPost();
    
    
}
