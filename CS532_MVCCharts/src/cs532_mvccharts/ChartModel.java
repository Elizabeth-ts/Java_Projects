/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs532_mvccharts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Arvin
 */
class ChartModel {

    private double[] gpa;
    private String[] courseName;
    private int[] credits;
    private String[] gpaKeyList = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};
    private double[] gpaValueList = {4, 4, 3.7, 3.3, 3, 2.7, 2.3, 2, 1.7, 1.3, 1, 0.7, 0};
    private Map<String, Double> gpaMap = new HashMap<>();
    private final ArrayList<ActionListener> actionListenerList = new ArrayList<>();

    public ChartModel() {
        for (int i = 0; i < gpaKeyList.length; i++) {
            gpaMap.put(gpaKeyList[i], gpaValueList[i]);
        }
    }

    public void addActionListener(ActionListener l) {
        actionListenerList.add(l);
    }

    public void removeActionListener(ActionListener l) {
        actionListenerList.remove(l);
    }

    private void processEvent(ActionEvent e) {
        for (ActionListener listener : actionListenerList) {
            listener.actionPerformed(e);
        }
    }

    @Override
    public String toString() {
        return "ChartModel{" + "data=" + gpa + ", dataName=" + courseName + '}';
    }

    public void setChartData(String[] newDataName, double[] newData, int[] newCredit) {
        courseName = newDataName;
        gpa = newData;
        credits = newCredit;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "test"));
    }

    public double[] getGpa() {
        return gpa;
    }

    public String[] getCourseName() {
        return courseName;
    }

    public int[] getCredits() {
        return credits;
    }

    public double dataSum() {
        double dataSum = 0.0;
        for (double d : gpa) {
            dataSum += d;
        }
        return dataSum;
    }

    public double calculateAverageGpa() {
        double totalGpa = 0, totalCredit = 0;

        for (int i = 0; i < credits.length; i++) {
            totalGpa = totalGpa + (credits[i] * gpa[i]);
            totalCredit += credits[i];
        }
        return totalGpa / totalCredit;
    }
}
