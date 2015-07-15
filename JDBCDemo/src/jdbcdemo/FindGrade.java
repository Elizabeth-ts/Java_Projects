/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcdemo;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class FindGrade extends JFrame {

    private JTextField jtfSSN = new JTextField(9);
    private JTextField jtfCourseId = new JTextField(5);
    private JButton jbtShowGrade = new JButton("Show Grade");

    // Statement for executing queries
    private Statement stmt;

    /**
     * Initialize the jFrame
     */
    public FindGrade() {
        // Initialize database connection and create a Statement object
        initializeDB();

        jbtShowGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbtShowGrade_actionPerformed(e);
            }
        });

        JPanel jPanel1 = new JPanel();
        jPanel1.add(new JLabel("SSN"));
        jPanel1.add(jtfSSN);
        jPanel1.add(new JLabel("Course ID"));
        jPanel1.add(jtfCourseId);
        jPanel1.add(jbtShowGrade);

        add(jPanel1, BorderLayout.NORTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Find Grades");
        setSize(480, 80);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void initializeDB() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            System.out.println("Database connected");

            // Create a statement
            stmt = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbtShowGrade_actionPerformed(ActionEvent e) {
        String ssn = jtfSSN.getText();
        String courseId = jtfCourseId.getText();
        try {
            String queryString = "select firstName, mi, "
                    + "lastName, title, grade from Student, Enrollment, Course "
                    + "where Student.ssn = '" + ssn + "' and Enrollment.courseId "
                    + "= '" + courseId
                    + "' and Enrollment.courseId = Course.courseId "
                    + " and Enrollment.ssn = Student.ssn";

            ResultSet rset = stmt.executeQuery(queryString);

            if (rset.next()) {
                String lastName = rset.getString(1);
                String mi = rset.getString(2);
                String firstName = rset.getString(3);
                String title = rset.getString(4);
                String grade = rset.getString(5);

                // Display result in a dialog box
                JOptionPane.showMessageDialog(null, firstName + " " + mi
                        + " " + lastName + "'s grade on course " + title + " is "
                        + grade);
            } else {
                // Display result in a dialog box
                JOptionPane.showMessageDialog(null, "Not found");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        FindGrade frame = new FindGrade();
        
    }
}
