/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circlemvc;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class CircleController extends JFrame {

    JTextField txtRadius = new JTextField("0.0");
    JComboBox cbFill = new JComboBox(new String[]{"false", "true"});
    CircleModel model;

    public CircleController(CircleModel model) {
        this.model = model;

        this.setTitle("Circle Controller");
        this.setLayout(new GridLayout(2, 2));
        this.getContentPane().add(new JLabel("Radius"));
        this.getContentPane().add(txtRadius);
        this.getContentPane().add(new JLabel("Filled"));
        this.getContentPane().add(cbFill);

        txtRadius.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double radius = Double.parseDouble(txtRadius.getText());
                model.setRadius(radius);
            }
        });

        cbFill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean filled = Boolean.parseBoolean(cbFill.getSelectedItem().toString());
                model.setFilled(filled);;
            }
        });

        this.setSize(300, 100);
        this.setVisible(true);
    }
}
