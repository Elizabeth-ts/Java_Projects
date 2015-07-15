/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBUpdateService;

import domain.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

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
    public void addUser(User newUser) throws RemoteException ;

    /**
     * Remove User by giving an User object
     *
     * @param userToBeRemove
     * @return remove success or not
     * @throws java.rmi.RemoteException
     */
    public boolean removeUser(User userToBeRemove) throws RemoteException ;

    /**
     * return the whole database data
     *
     * @return set of user data
     * @throws java.rmi.RemoteException
     */
    public ResultSet getAllUser() throws RemoteException ;

}
