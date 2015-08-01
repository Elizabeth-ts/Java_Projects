/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Arvin
 */
public class TitledBorderTest {

    private static void createAndShowUI() {
        //UIManager.getDefaults().put("TitledBorder.titleColor", Color.RED);
        Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        TitledBorder title = BorderFactory.createTitledBorder(lowerEtched, "Title");
//      title.setTitleJustification(TitledBorder.RIGHT);
        Font titleFont = UIManager.getFont("TitledBorder.font");
        //title.setTitleFont(titleFont.deriveFont(Font.ITALIC + Font.BOLD));

        JPanel panel = new JPanel() ;
        panel.setBorder(title);

        JFrame frame = new JFrame("SSCCE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(200, 200);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });
    }
}
