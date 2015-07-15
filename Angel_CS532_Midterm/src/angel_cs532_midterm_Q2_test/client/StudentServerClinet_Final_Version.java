/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package angel_cs532_midterm_Q2_test.client;

import angel_cs532_midterm_Q2_test.Student;
import angel_cs532_midterm_Q2_test.server.StudentServerInterface;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Arvin
 */
public class StudentServerClinet_Final_Version extends JPanel {

    private final JButton jbtAdd, jbtRemove, jbtSearch, jbtGetAllBad;
    private final JTextArea jtaResult, jtaName, jtaScore;
    private StudentServerInterface student;
    private final String host = "localhost";
    private final String registry = "StudentServerInterfaceImpl";

    public StudentServerClinet_Final_Version() {
        jbtAdd = new JButton("Add");
        jbtRemove = new JButton("Remove");
        jbtSearch = new JButton("Search");
        jbtGetAllBad = new JButton("GetAllBad");
        jtaResult = new JTextArea(5, 30);
        jtaName = new JTextArea(1, 20);
        jtaScore = new JTextArea(1, 20);

        initializeRMI();
        initialComponents();
    }

    public void initialComponents() {
        JPanel panelName = new JPanel(new BorderLayout());
        JPanel panelScore = new JPanel(new BorderLayout());
        JPanel panelButtons = new JPanel(new GridLayout(1, 4));

        panelName.add(new JLabel("Name: "), BorderLayout.WEST);
        panelName.add(jtaName, BorderLayout.CENTER);

        panelScore.add(new JLabel("Score: "), BorderLayout.WEST);
        panelScore.add(jtaScore, BorderLayout.CENTER);

        panelButtons.add(jbtAdd);
        panelButtons.add(jbtRemove);
        panelButtons.add(jbtSearch);
        panelButtons.add(jbtGetAllBad);

        setLayout(new GridLayout(3, 1));
        add(panelName);
        add(panelScore);
        add(panelButtons);

        jbtAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jtaName.getText();
                String score = jtaScore.getText();
                if (name.isEmpty() || score.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing name or score input", "Error!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    addStudent(name, Double.parseDouble(score));
                    JOptionPane.showMessageDialog(null, "Data added!", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    trace("Data Send:");
                    trace("Name: " + name);
                    trace("Score: " + score);
                } catch (NumberFormatException | RemoteException ex) {
                    Logger.getLogger(StudentServerClinet_Final_Version.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jbtRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jtaName.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing name", "Error!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    removeStudent(name);
                    JOptionPane.showMessageDialog(null, "Data Remove!", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    trace("Data Remove:");
                    trace("Name: " + name);
                } catch (RemoteException ex) {
                    Logger.getLogger(StudentServerClinet_Final_Version.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jbtSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jtaName.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing name", "Error!", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    jtaScore.setText(String.valueOf(searchScore(name)));
                    trace("Data Retrieve:");
                    trace("Name: " + name);
                } catch (RemoteException ex) {
                    Logger.getLogger(StudentServerClinet_Final_Version.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jbtGetAllBad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setLocationRelativeTo(null);
                frame.setSize(300, 200);
                frame.setVisible(true);
                frame.setTitle("Bad Students");
                frame.add(new JScrollPane(jtaResult));
                try {

                    List<Student> list = getAllBadStudent();
                    for (Student s : list) {
                        jtaResult.append(s.toString() + "\n");
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(StudentServerClinet_Final_Version.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void addStudent(String name, double score) throws RemoteException {
        student.addStudent(name, score);
    }

    private double searchScore(String name) throws RemoteException {
        return student.searchScore(name.trim());
    }

    private void removeStudent(String name) throws RemoteException {
        student.removeStudent(name);
    }

    private ArrayList<Student> getAllBadStudent() throws RemoteException {
        return student.getAllBadStudents();
    }

    /**
     * Initialize RMI
     */
    protected void initializeRMI() {

        try {
            Registry reg = LocateRegistry.getRegistry(host);
            student = (StudentServerInterface) reg.lookup(registry);
            trace("Server object " + student + "found");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(StudentServerClinet_Final_Version.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void trace(String s) {
        System.out.println(s);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame();
        frame.add(new StudentServerClinet_Final_Version());
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setTitle("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
