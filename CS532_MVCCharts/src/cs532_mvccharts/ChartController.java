/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs532_mvccharts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Arvin
 */
class ChartController extends JFrame {

    private final ArrayList<JTextField> jtfDataList = new ArrayList<>();
    private final ArrayList<JTextField> jtfDataNameList = new ArrayList<>();
    private final ArrayList<JTextField> jtfCreditsList = new ArrayList<>();
    private final JLabel jlbGPA = new JLabel("GPA");
    private final JLabel jlbCourseName = new JLabel("Course Name");
    private final JLabel jlbCredits = new JLabel("Credits");
    private final JButton jbtSave = new JButton("Save");
    private final JButton jbtAdd = new JButton("Add new data");
    private final ChartModel model;

    public ChartController(ChartModel model) {
        this.model = model;
        final GridLayout myLayout = new GridLayout(0, 3);
        final JPanel dataPanel = new JPanel(myLayout);
        final JPanel buttonPanel = new JPanel(new FlowLayout());
        final JPanel labelPanel = new JPanel(new GridLayout(1, 3));
        setTitle("Chart Controller");
        setLayout(new BorderLayout());
        //setLayout(new GridLayout(3, 2));
        add(dataPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(labelPanel, BorderLayout.NORTH);
        buttonPanel.add(jbtAdd);
        buttonPanel.add(jbtSave);

        jlbGPA.setHorizontalAlignment(JLabel.CENTER);
        jlbCourseName.setHorizontalAlignment(JLabel.CENTER);
        jlbCredits.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(jlbCourseName);
        labelPanel.add(jlbGPA);
        labelPanel.add(jlbCredits);

        jbtAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField jtfDataName = new JTextField("Empty");
                JTextField jtfData = new JTextField("0");
                JTextField jtfCredits = new JTextField("0");
                myLayout.setRows(myLayout.getRows() + 1);
                dataPanel.add(jtfDataName);
                dataPanel.add(jtfData);
                dataPanel.add(jtfCredits);
                dataPanel.revalidate();
                dataPanel.repaint();
                jtfDataList.add(jtfData);
                jtfDataNameList.add(jtfDataName);
                jtfCreditsList.add(jtfCredits);
            }
        });
        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = jtfDataList.size();
                double[] gpa = new double[count];
                String[] courseName = new String[count];
                double result;
                int[] credits = new int[count];
                for (int i = 0; i < count; i++) {
                    gpa[i] = Double.parseDouble(jtfDataList.get(i).getText());
                    courseName[i] = jtfDataNameList.get(i).getText();
                    credits[i] = Integer.parseInt(jtfCreditsList.get(i).getText());
                }
                model.setChartData(courseName, gpa, credits);
                result = model.calculateAverageGpa();
                JOptionPane.showMessageDialog(null,
                        "Your average GPA is: " + new DecimalFormat("0.00").format(result),
                        "result",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        setSize(500, 150);
        setVisible(true);
    }
}
