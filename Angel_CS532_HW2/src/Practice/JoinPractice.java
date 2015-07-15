/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arvin
 */
public class JoinPractice implements Runnable {

    @Override
    public void run() {
        Thread t = new Thread(new PrintChar());
        t.start();
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            if (i == 0) {
                try {
                    t.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(JoinPractice.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new JoinPractice());
        t.start();
    }
}

class PrintChar implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 40; i++) {
            System.out.println("c");
        }
    }
}
