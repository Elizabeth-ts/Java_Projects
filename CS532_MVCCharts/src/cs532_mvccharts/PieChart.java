/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs532_mvccharts;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Arvin
 */
class PieChart extends JFrame implements ActionListener {

    class MyPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            double radians, dataTotal = model.dataSum();
            int startAngle = 0;
            int x, y, radius, xCenter, yCenter;
            xCenter = getWidth() / 2;
            yCenter = getHeight() / 2;
            radius = (int) (Math.min(getWidth(), getHeight()) * 0.4);
            x = xCenter - radius;
            y = yCenter - radius;
            updateList();
            for (int i = 0; i < model.getData().length; i++) {
                int endAngle = (int) (360 * model.getData()[i] / dataTotal);
                g.setColor(colorList[i % 5]);
                g.fillArc(x, y, 2 * radius, 2 * radius, startAngle, endAngle);
                radians = Math.toRadians(startAngle + endAngle / 2);
                int ax = (int) (xCenter + (radius * Math.cos(radians)));
                int ay = (int) (yCenter - (radius * Math.sin(radians)));
                labelList.get(i).setSize(labelList.get(i).getPreferredSize());
                labelList.get(i).setLocation(ax, ay);
                startAngle += endAngle;
            }
            g.fillArc(x, y, 2 * radius, 2 * radius, startAngle, 360 - startAngle);
        }
    }
    private ChartModel model;
    private final Color[] colorList = {Color.pink, Color.red, Color.white, Color.green, Color.cyan};
    private final ArrayList<JLabel> labelList = new ArrayList<>();
    private final MyPanel panel = new MyPanel();

    public PieChart(ChartModel model) {
        this.model = model;
        model.addActionListener(this);

        setTitle("Pie Chart");

        panel.setLayout(null);
        updateList();
        getContentPane().add(panel);
        setSize(300, 300);
        setVisible(true);
    }

    public void updateList() {
        panel.removeAll();
        labelList.clear();
        for (String s : model.getDataName()) {
            JLabel jlb = new JLabel(s);
            labelList.add(jlb);
            panel.add(jlb);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        panel.repaint();
    }
}
