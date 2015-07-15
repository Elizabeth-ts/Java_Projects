/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs532_mvccharts;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Arvin
 */
class ChartModel {

    private double[] data;
    private String[] dataName;
    private final ArrayList<ActionListener> actionListenerList = new ArrayList<>();

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
        return "ChartModel{" + "data=" + data + ", dataName=" + dataName + '}';
    }

    public void setChartData(String[] newDataName, double[] newData) {
        dataName = newDataName;
        data = newData;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "test"));
    }

    public double[] getData() {
        return data;
    }

    public String[] getDataName() {
        return dataName;
    }

    public double dataSum() {
        double dataSum = 0.0;
        for (double d : data) {
            dataSum += d;
        }
        return dataSum;
    }
}
