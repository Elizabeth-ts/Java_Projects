/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs532_example;

/**
 *
 * @author Arvin
 */
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
public class DrawArcs extends JFrame {
 public DrawArcs() {
 setTitle("DrawArcs");
 ArcsPanel a = new ArcsPanel();
 add(a);

 
 setLocationRelativeTo(null); // Center the frame
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setSize(250, 300);
 setVisible(true);
 }
 /** Main method */
 public static void main(String[] args) {
 DrawArcs frame = new DrawArcs();
 }
}
// The class for drawing arcs on a panel
class ArcsPanel extends JPanel {

 @Override
 protected void paintComponent(Graphics g) {
 super.paintComponent(g);
 int xCenter = getWidth() / 2;
 int yCenter = getHeight() / 2;
 int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);
 int x = xCenter - radius;
 int y = yCenter - radius;
 g.fillArc(x, y, 2 * radius, 2 * radius, 0, 90);
 g.setColor(Color.yellow);
 g.fillArc(x, y, 2 * radius, 2 * radius, 90, 90);
 g.setColor(Color.BLUE);
 g.fillArc(x, y, 2 * radius, 2 * radius, 180, 90);
 g.setColor(Color.RED);
 g.fillArc(x, y, 2 * radius, 2 * radius, 270, 90);
 }
}
