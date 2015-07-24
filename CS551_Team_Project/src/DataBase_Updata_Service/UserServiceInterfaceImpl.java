/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase_Updata_Service;

import SQL_PreparedStatements.*;
import domain.Post;
import domain.StepRecord;
import domain.UserPackage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class UserServiceInterfaceImpl extends UnicastRemoteObject
        implements UserServiceInterface, TableQuery_Post, TableQuery_StepRecord, TableQuery_User {

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
            jdbcConnection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean addUser(UserPackage newUser) throws RemoteException {
        try {
            preparedStat = jdbcConnection.prepareStatement(TableQuery_User.INSERT);
            preparedStat.setString(1, newUser.getUserName());
            preparedStat.setString(2, newUser.getPhoneNumber());
            preparedStat.executeUpdate();
            jdbcConnection.commit();
            jdbcResultSet = jdbcStatement.executeQuery("select last_insert_id() as last_id");
            if (jdbcResultSet.next()) {
                newUser.setUserId(jdbcResultSet.getInt("last_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeUser(UserPackage userToBeRemove) throws RemoteException {
        try {
            preparedStat = jdbcConnection.prepareStatement(TableQuery_User.DELETE);
            preparedStat.setString(1, userToBeRemove.getPhoneNumber());
            preparedStat.execute();
            jdbcConnection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean insertNewRecord(UserPackage user) throws RemoteException {
        StepRecord data = user.getStepRecord();
        try {
            preparedStat = jdbcConnection.prepareStatement(TableQuery_StepRecord.INSERT);
            preparedStat.setInt(1, data.getStepCount());
            preparedStat.setTimestamp(2, data.getRecordStartDate());
            preparedStat.setTimestamp(3, data.getRecordEndDate());
            preparedStat.setString(4, user.getPhoneNumber());
            preparedStat.executeUpdate();
            jdbcConnection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public UserPackage searchStepTotalForUserWithinPeriod(UserPackage user) throws RemoteException {
        int totalStepCount = 0;
        try {
            preparedStat = jdbcConnection.prepareStatement(TableQuery_StepRecord.SELECT);
            preparedStat.setString(1, user.getPhoneNumber());
            preparedStat.setTimestamp(2, user.getStepRecord().getRecordStartDate());
            preparedStat.setTimestamp(3, user.getStepRecord().getRecordEndDate());
            jdbcResultSet = preparedStat.executeQuery();
            if (jdbcResultSet != null) {
                while (jdbcResultSet.next()) {
                    totalStepCount += jdbcResultSet.getInt(TABLE_COLUMN_NAME_STEP);
                }
            }
            user.getStepRecord().setStepCount(totalStepCount);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return user;

    }

    @Override
    public Stack<String> searchContactList(Stack<String> contactList) throws RemoteException {
        final Stack<String> confirmList = new Stack<>();
        System.out.println(contactList);
        try {
            preparedStat = jdbcConnection.prepareStatement("select phone_number from cs551_team_project.user");
            jdbcResultSet = preparedStat.executeQuery();
            if (jdbcResultSet != null) {
                while (jdbcResultSet.next()) {
                    String phoneNumber = jdbcResultSet.getString(TableQuery_User.TABLE_COLUMN_NAME_PHONE_NUMBER);
                    if (contactList.contains(phoneNumber)) {
                        confirmList.push(phoneNumber);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(confirmList);
        return confirmList;
    }

    @Override
    public boolean addPost(UserPackage user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Stack<Post> getPost(UserPackage user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
