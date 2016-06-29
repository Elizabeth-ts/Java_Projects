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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Arvin
 */
class ChartController extends JFrame {

    private final ArrayList<JComboBox> jcbGpaList = new ArrayList<>();
    private final ArrayList<JTextField> jtfCourseNameList = new ArrayList<>();
    private final ArrayList<JSpinner> jspCreditsList = new ArrayList<>();
    private final JLabel jlbGPA = new JLabel("GPA");
    private final JLabel jlbCourseName = new JLabel("Course Name");
    private final JLabel jlbCredits = new JLabel("Credits");
    private final JButton jbtSave = new JButton("Save");
    private final JButton jbtAdd = new JButton("Add new data");
    private final ChartModel model;
    private boolean triggered = false;

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
                JTextField jtfCourseName = new JTextField("Empty");
                JComboBox jcbGpa = new JComboBox(model.getGpaKeyList());
                SpinnerNumberModel numModel = new SpinnerNumberModel(1, 0, 5, 1);
                JSpinner jspCredits = new JSpinner(numModel);
                myLayout.setRows(myLayout.getRows() + 1);
                dataPanel.add(jtfCourseName);
                dataPanel.add(jcbGpa);
                dataPanel.add(jspCredits);
                dataPanel.revalidate();
                dataPanel.repaint();
                jcbGpaList.add(jcbGpa);
                jtfCourseNameList.add(jtfCourseName);
                jspCreditsList.add(jspCredits);
            }
        });
        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = jcbGpaList.size();
                double[] gpa = new double[count];
                String[] courseName = new String[count];
                double result;
                int[] credits = new int[count];
                for (int i = 0; i < count; i++) {
                    gpa[i] = model.getGpaMap().get(jcbGpaList.get(i).getSelectedItem().toString());
                    //gpa[i] = Double.parseDouble(jcbGpaList.get(i).getSelectedItem().toString());
                    courseName[i] = jtfCourseNameList.get(i).getText();
                    credits[i] = (Integer) jspCreditsList.get(i).getValue();
                }
                model.setChartData(courseName, gpa, credits);
                result = model.calculateAverageGpa();
                if(!triggered) {
                    triggered = true;
                    return;
                }
                JOptionPane.showMessageDialog(null,
                        "Your average GPA is: " + new DecimalFormat("0.00").format(result),
                        "result",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        setSize(500, 150);
        setVisible(true);
        jbtAdd.doClick();
        jbtSave.doClick();
    }
}
