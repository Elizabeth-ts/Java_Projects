/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBUpdateService;

import domain.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class UserServiceInterfaceImpl extends UnicastRemoteObject implements UserServiceInterface, User_DBQuery_Setting {

    private Connection jdbcConnection;
    private Statement jdbcStatement;
    private ResultSet jdbcResultSet;
    private PreparedStatement preparedStat;

    public UserServiceInterfaceImpl() throws RemoteException {
        try {
            // Get connection from local database
            jdbcConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            jdbcConnection.setAutoCommit(false);
            // Create statement
            jdbcStatement = jdbcConnection.createStatement();
            jdbcStatement.execute(QUERY_CREATE_DATABASE);
            jdbcStatement.execute(QUERY_CREATE_TABLE);
            jdbcConnection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addUser(User newUser) throws RemoteException {
        try {
            preparedStat = jdbcConnection.prepareStatement(QUERY_INSERT);
            preparedStat.setLong(COLUMN_INDEX_USERID, newUser.getUserId());
            preparedStat.setString(COLUMN_INDEX_USERNAME, newUser.getUserName());
            preparedStat.setInt(COLUMN_INDEX_USERAGE, newUser.getUserAge());
            preparedStat.setDouble(COLUMN_INDEX_USERHEIGHT, newUser.getUserHeight());
            preparedStat.setDouble(COLUMN_INDEX_USERWEIGHT, newUser.getUserWeight());
            preparedStat.executeUpdate();
            jdbcConnection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean removeUser(User userToBeRemove) throws RemoteException {
        try {
            preparedStat = jdbcConnection.prepareStatement(QUERY_DROP_RECORD);
            preparedStat.setLong(1, userToBeRemove.getUserId());
            preparedStat.execute();
            jdbcConnection.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return false;
        }

    }

    @Override
    public ResultSet getAllUser() throws RemoteException {
        try {
            preparedStat = jdbcConnection.prepareStatement(QUERY_SELECT);
            jdbcResultSet = preparedStat.executeQuery();
            while (jdbcResultSet.next()) {
                System.out.println(jdbcResultSet.getString(TABLE_COLUMN_NAME_USERNAME));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return jdbcResultSet;
        }
    }

    private void trace(String s) {
        System.out.println(s);
    }
}
