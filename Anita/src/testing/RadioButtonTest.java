/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Arvin
 */
public class RadioButtonTest extends JPanel{
    
    public RadioButtonTest(){
        setLayout(new GridLayout(3,1));
        JRadioButton a,b,c;
        a= new JRadioButton("a");
        b= new JRadioButton("b");
        c= new JRadioButton("c");
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(a);
        bg.add(b);
        bg.add(c);
        
        add(a);
        add(b);
        add(c);
    }
    
    
    
    
    
    public static void main(String[] args){
                JFrame frame = new JFrame("SSCCE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new RadioButtonTest());
        frame.setSize(200, 200);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
