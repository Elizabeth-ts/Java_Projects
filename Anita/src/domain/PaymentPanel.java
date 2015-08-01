/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Arvin
 */
public class PaymentPanel extends JPanel {

    private final PaymentMethodPanel methodPanel = new PaymentMethodPanel();
    private final PaymentInformationPanel informationPanel = new PaymentInformationPanel();

    public PaymentPanel() {
        setLayout(new GridLayout(2, 1));
        add(methodPanel);
        add(informationPanel);

        // Add RadioButtonListener to all RadioButtons in PaymentMethodPanel
        RadioButtonListener rbListener = new RadioButtonListener();
        methodPanel.getJrbCreditCard().addActionListener(rbListener);
        methodPanel.getJrbDebit().addActionListener(rbListener);
        methodPanel.getJrbCash().addActionListener(rbListener);

    }

    class RadioButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (methodPanel.getJrbCreditCard().isSelected() || methodPanel.getJrbDebit().isSelected()) {
                informationPanel.enableAllTextField();
            } else if (methodPanel.getJrbCash().isSelected()) {
                informationPanel.disableAllTextField();
            }
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SSCCE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PaymentPanel());
        frame.setSize(700, 300);
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }
}
