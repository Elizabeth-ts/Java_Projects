/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs532_mvccharts;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Arvin
 */
class ChartController extends JFrame {

    private final ArrayList<JTextField> jtfDataList = new ArrayList<>();
    private final ArrayList<JTextField> jtfDataNameList = new ArrayList<>();
    private final JLabel jlbData = new JLabel("Data");
    private final JLabel jlbDataName = new JLabel("Data Name");
    private final JButton jbtSave = new JButton("Save");
    private final JButton jbtAdd = new JButton("Add new data");
    private final ChartModel model;

    public ChartController(ChartModel model) {
        this.model = model;
        final GridLayout myLayout = new GridLayout(0, 2);
        final JPanel dataPanel = new JPanel(myLayout);
        final JPanel buttonPanel = new JPanel(new FlowLayout());
        final JPanel labelPanel = new JPanel(new GridLayout(1, 2));
        setTitle("Chart Controller");
        setLayout(new BorderLayout());
        //setLayout(new GridLayout(3, 2));
        add(dataPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(labelPanel, BorderLayout.NORTH);
        buttonPanel.add(jbtAdd);
        buttonPanel.add(jbtSave);

        jlbData.setHorizontalAlignment(JLabel.CENTER);
        jlbDataName.setHorizontalAlignment(JLabel.CENTER);
        labelPanel.add(jlbDataName);
        labelPanel.add(jlbData);

        jbtAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField jtfDataName = new JTextField("Empty");
                JTextField jtfData = new JTextField("2");
                myLayout.setRows(myLayout.getRows() + 1);
                dataPanel.add(jtfDataName);
                dataPanel.add(jtfData);
                dataPanel.revalidate();
                dataPanel.repaint();
                jtfDataList.add(jtfData);
                jtfDataNameList.add(jtfDataName);
            }
        });
        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = jtfDataList.size();
                double[] data = new double[count];
                String[] dataName = new String[count];
                for (int i = 0; i < count; i++) {
                    data[i] = Double.parseDouble(jtfDataList.get(i).getText());
                    dataName[i] = jtfDataNameList.get(i).getText();
                }
                model.setChartData(dataName, data);
            }
        });

        setSize(500, 150);
        setVisible(true);
    }
}
