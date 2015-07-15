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
/**
 *
 * @author ken
 */


public class CircleMain extends JFrame {

    JButton btnController = new JButton("Controller");
    JButton btnView = new JButton("View");
    CircleModel model = new CircleModel();

    public CircleMain() {
        this.setLayout(new GridLayout(2, 1));
        this.getContentPane().add(btnController);
        this.getContentPane().add(btnView);

        btnController.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CircleController controller = new CircleController(model);
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CircleView view = new CircleView(model);
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        CircleMain frame = new CircleMain();

    }
}