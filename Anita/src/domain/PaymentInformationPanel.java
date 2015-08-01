/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author
 */
public class PaymentInformationPanel extends JPanel {

    private JLabel labelPaymentAmount, labelCreditCardNumber, labelCheckingAccountNumber,
            labelSecurityCode, labelRoutingNumber;
    private JTextField jtfPaymentAmount, jtfCreditCardNumber, jtfCheckingAccountNumber,
            jtfSecurityCode, jtfRoutingNumber;

    public PaymentInformationPanel() {
        // Initialize all the components
        initialComponents();

        setLayout(new GridLayout(3, 4));
        add(labelPaymentAmount);
        add(jtfPaymentAmount);
        add(new JLabel());
        add(new JLabel());

        add(labelCreditCardNumber);
        add(jtfCreditCardNumber);

        add(labelSecurityCode);
        add(jtfSecurityCode);

        add(labelCheckingAccountNumber);
        add(jtfCheckingAccountNumber);

        add(labelRoutingNumber);
        add(jtfRoutingNumber);

    }

    // Initial all the components ready for use later
    private void initialComponents() {
        labelPaymentAmount = new JLabel("Payment Amount:");
        labelCreditCardNumber = new JLabel("Credit Card Number:");
        labelCheckingAccountNumber = new JLabel("Checking Account Number:");
        labelSecurityCode = new JLabel("Security Code:");
        labelRoutingNumber = new JLabel("Routing number:");
        jtfPaymentAmount = new JTextField(200);
        jtfCreditCardNumber = new JTextField(20);
        jtfCheckingAccountNumber = new JTextField(20);
        jtfSecurityCode = new JTextField(20);
        jtfRoutingNumber = new JTextField(20);
    }

    public void enableAllTextField() {
        jtfPaymentAmount.setEditable(true);
        jtfCreditCardNumber.setEditable(true);
        jtfCheckingAccountNumber.setEditable(true);
        jtfSecurityCode.setEditable(true);
        jtfRoutingNumber.setEditable(true);
    }

    public void disableAllTextField() {
        //jtfPaymentAmount.setEditable(false);
        jtfCreditCardNumber.setEditable(false);
        jtfCheckingAccountNumber.setEditable(false);
        jtfSecurityCode.setEditable(false);
        jtfRoutingNumber.setEditable(false);
    }
/*
    public static void main(String[] args) {
        JFrame frame = new JFrame("SSCCE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PaymentInformationPanel());
        frame.setSize(200, 200);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
*/
}
