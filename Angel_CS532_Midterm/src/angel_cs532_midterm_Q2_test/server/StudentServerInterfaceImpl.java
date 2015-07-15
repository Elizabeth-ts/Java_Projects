package angel_cs532_midterm_Q2_test.server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import angel_cs532_midterm_Q2_test.Student;
import java.rmi.*;
import java.rmi.server.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentServerInterfaceImpl
        extends UnicastRemoteObject
        implements StudentServerInterface, MySQL_DB_Query {

    private Connection jdbcConnection = null;
    private Statement jdbcStatement = null;
    private ResultSet jdbcResultSet = null;
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String user = "root";
    private final String password = "johnson5414";

    public StudentServerInterfaceImpl() throws RemoteException, SQLException {
        // Get a connection to local database
        jdbcConnection = DriverManager.getConnection(url, user, password);
        // create a statement
        jdbcStatement = jdbcConnection.createStatement();
        jdbcStatement.execute(CREATE_DATABASE);
        jdbcStatement.execute(CREATE_TABLE);
    }

    /**
     * Implement the searchScore method from the Student interface
     */
    @Override
    public double searchScore(String name) throws RemoteException {
        try {
            jdbcResultSet = jdbcStatement.executeQuery("SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME
                    + " WHERE NAME = '" + name + "';");
            if (jdbcResultSet.next()) {
                return jdbcResultSet.getDouble(TABLE_COLUMN_SCORE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentServerInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public void addStudent(String name, double score) throws RemoteException {
        try {
            jdbcStatement.execute("INSERT INTO " + DATABASE_NAME + "." + TABLE_NAME
                    + " (" + TABLE_COLUMN_NAME + ", " + TABLE_COLUMN_SCORE + ") VALUES ('"
                    + name + "', " + score + ");"
            );
        } catch (SQLException ex) {
            Logger.getLogger(StudentServerInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeStudent(String name) throws RemoteException {
        try {
            jdbcStatement.execute("DELETE FROM " + DATABASE_NAME + "." + TABLE_NAME
                    + " WHERE NAME='" + name + "';");
        } catch (SQLException ex) {
            Logger.getLogger(StudentServerInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Student> getAllBadStudents() throws RemoteException {
        ArrayList<Student> list = new ArrayList<>();
        try {
            jdbcResultSet = jdbcStatement.executeQuery("SELECT * FROM " + DATABASE_NAME + "." + TABLE_NAME
                    + " WHERE SCORE < 50;");
            while (jdbcResultSet.next()) {
                list.add(new Student(jdbcResultSet.getString(TABLE_COLUMN_NAME), jdbcResultSet.getDouble(TABLE_COLUMN_SCORE)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentServerInterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
