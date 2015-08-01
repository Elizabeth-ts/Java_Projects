/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author
 */
public class PaymentMethodPanel extends JPanel {

    // JRadioButtons 
    private JRadioButton jrbCreditCard, jrbDebit, jrbCash;
    // ButtonGroup
    private ButtonGroup bg;
    // Border
    private Border lowerEtched;
    // TitledBorder
    private TitledBorder title;

    /**
     * PaymentMethodPanel constructor
     */
    public PaymentMethodPanel() {
        // Initialize all the components
        initialComponents();

        // Setup for Panel layout
        setLayout(new GridLayout(3, 1));

        // Group all the RadioButton together
        bg.add(jrbCreditCard);
        bg.add(jrbDebit);
        bg.add(jrbCash);

        // Add components to panel
        add(jrbCreditCard);
        add(jrbDebit);
        add(jrbCash);

        // Panel Border Setup
        setBorder(title);

        // Credit Card RadioButton preSelected
        jrbCreditCard.setSelected(true);
    }

    // Initial all the components ready for use later
    private void initialComponents() {
        jrbCreditCard = new JRadioButton("Credit Card");
        jrbDebit = new JRadioButton("Debit");
        jrbCash = new JRadioButton("Cash");
        bg = new ButtonGroup();
        lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        title = BorderFactory.createTitledBorder(lowerEtched, "Title");
    }

    public JRadioButton getJrbCreditCard() {
        return jrbCreditCard;
    }

    public JRadioButton getJrbDebit() {
        return jrbDebit;
    }

    public JRadioButton getJrbCash() {
        return jrbCash;
    }
    /*
     public static void main(String[] args) {
     JFrame frame = new JFrame("SSCCE");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.add(new PaymentMethodPanel());
     frame.setSize(200, 200);
     frame.setLocationByPlatform(true);
     frame.setVisible(true);
     }
     */
}
