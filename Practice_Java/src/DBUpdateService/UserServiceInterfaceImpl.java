/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBUpdateService;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import domain.StepRecord;
import domain.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class UserServiceInterfaceImpl extends UnicastRemoteObject
        implements UserServiceInterface, User_TableQuery_Setting, StepData_TableQuery_Setting {

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
            preparedStat = jdbcConnection.prepareStatement(User_TableQuery_Setting.QUERY_INSERT);
            preparedStat.setString(User_TableQuery_Setting.COLUMN_INDEX_USERNAME, newUser.getUserName());
            preparedStat.setInt(User_TableQuery_Setting.COLUMN_INDEX_USERAGE, newUser.getUserAge());
            preparedStat.setDouble(User_TableQuery_Setting.COLUMN_INDEX_USERHEIGHT, newUser.getUserHeight());
            preparedStat.setDouble(User_TableQuery_Setting.COLUMN_INDEX_USERWEIGHT, newUser.getUserWeight());
            preparedStat.executeUpdate();
            jdbcConnection.commit();
            jdbcResultSet = jdbcStatement.executeQuery("select last_insert_id() as last_id");
            if (jdbcResultSet.next()) {
                newUser.setUserId(jdbcResultSet.getInt("last_id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean removeUser(User userToBeRemove) throws RemoteException {
        try {
            preparedStat = jdbcConnection.prepareStatement(User_TableQuery_Setting.QUERY_DROP_RECORD);
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


    public ArrayList<User> getAllUser() throws RemoteException {
        ArrayList<User> list = new ArrayList<>();
        try {
            preparedStat = jdbcConnection.prepareStatement(User_TableQuery_Setting.QUERY_SELECT);
            jdbcResultSet = preparedStat.executeQuery();

            while (jdbcResultSet.next()) {
                System.out.println(jdbcResultSet.getString(User_TableQuery_Setting.TABLE_COLUMN_NAME_USERNAME));
                list.add(new User(jdbcResultSet.getLong(User_TableQuery_Setting.TABLE_COLUMN_NAME_USERID),
                        jdbcResultSet.getString(User_TableQuery_Setting.TABLE_COLUMN_NAME_USERNAME),
                        jdbcResultSet.getInt(User_TableQuery_Setting.TABLE_COLUMN_NAME_USERAGE),
                        jdbcResultSet.getDouble(User_TableQuery_Setting.TABLE_COLUMN_NAME_USERHEIGHT),
                        jdbcResultSet.getDouble(User_TableQuery_Setting.TABLE_COLUMN_NAME_USERWEIGHT)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServiceInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return list;
        }
    }

    private void trace(String s) {
        System.out.println(s);
    }

    @Override
    public boolean insertNewRecord(StepRecord step) throws RemoteException, SQLException {

        preparedStat = jdbcConnection.prepareStatement(StepData_TableQuery_Setting.QUERY_INSERT);
        preparedStat.setInt(1, step.getStepCount());
        preparedStat.setTimestamp(2, step.getRecordStartTime());
        preparedStat.setLong(3, step.getUser().getUserId());
        try {
            preparedStat.executeUpdate();
        } catch (MySQLIntegrityConstraintViolationException ex) {
            trace("User not found!!!!!");
            jdbcConnection.rollback();
            return false;
        }
        jdbcConnection.commit();
        trace(preparedStat.toString());
        return true;

    }

    @Override
    public StepRecord searchStepTotalForUserWithinPeriod(StepRecord step) throws RemoteException, SQLException {
        int stepCount = 0;
        preparedStat = jdbcConnection.prepareStatement(StepData_TableQuery_Setting.QUERY_SELECT_WITH_TIME);
        preparedStat.setLong(1, step.getUser().getUserId());
        preparedStat.setTimestamp(2, step.getRecordStartTime());
        preparedStat.setTimestamp(3, step.getRecordEndTime());
        jdbcResultSet = preparedStat.executeQuery();
        trace(preparedStat.toString());
        if (jdbcResultSet != null) {
            while (jdbcResultSet.next()) {
                stepCount += jdbcResultSet.getInt(StepData_TableQuery_Setting.TABLE_COLUMN_NAME_STEP);
            }
            step.setStepCount(stepCount);
            System.out.println(step);
        } else {
            step.setStepCount(-1);
        }

        return step;
    }

}
