package angel_cs532_midterm_Q2_test.server;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import angel_cs532_midterm_Q2_test.Student;
import java.rmi.*;
import java.util.ArrayList;

public interface StudentServerInterface extends Remote {

    /**
     * Return the score for specified the name
     *
     * @param name the student name
     * @return an double score or –1 if the student is not found
     * @throws java.rmi.RemoteException
     */
    public double searchScore(String name) throws RemoteException;

    /**
     *
     * @param name
     * @param score
     * @throws RemoteException
     */
    public void addStudent(String name, double score) throws RemoteException;

    /**
     *
     * @param name
     * @throws RemoteException
     */
    public void removeStudent(String name) throws RemoteException;

    /**
     *
     * @return @throws RemoteException
     */
    public ArrayList<Student> getAllBadStudents() throws RemoteException;

}
