/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circlemvc;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

class CircleView extends JFrame implements ActionListener{

    JPanel panel;
    CircleModel model;

    public CircleView(CircleModel model) {
        this.model = model;
        model.addActionListener(this);
        
        this.setTitle("Circle View");
        this.panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int xCenter = getWidth() / 2;
                int yCenter = getHeight() / 2;
                int radius = (int) model.getRadius();

                if (model.getFilled()) {
                    g.fillOval(xCenter - radius, yCenter - radius, 2 * radius, 2 * radius);
                } else {
                    g.drawOval(xCenter - radius, yCenter - radius, 2 * radius, 2 * radius);
                }
            }
        };
        
        this.getContentPane().add(panel);

        this.setSize(300, 300);
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event) {
        this.panel.repaint();
    }
}
