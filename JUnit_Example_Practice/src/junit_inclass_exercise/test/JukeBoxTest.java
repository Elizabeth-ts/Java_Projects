/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit_inclass_exercise.test;

import junit_inclass_exercise.JukeBox;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Arvin
 */
public class JukeBoxTest {

    @Test
    public void testIsValidCreditCardNegitive() {
        trace("Negitive test start!");
        JukeBox jb = new JukeBox(null, 0);
        assertEquals("Result", false, jb.isValidCreditCard());
        trace("Negitive test end!");
    }

    @Test
    public void testIsValidCreditCardPositive() {
        trace("Positive test start!");
        JukeBox jb = new JukeBox(null, 0);
        assertEquals("Result", true, jb.isValidCreditCard());
        trace("Positive test end!");
    }

    private void trace(String s) {
        System.out.println(s);
    }
}
