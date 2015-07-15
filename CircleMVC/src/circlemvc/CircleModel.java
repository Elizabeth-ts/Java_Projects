/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circlemvc;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CircleModel {

    private double radius = 0.0;
    private boolean filled = false;
    private ArrayList<ActionListener> actionListenerList = new ArrayList<>();

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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "radius"));
    }

    public boolean getFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "filled"));
    }

}
