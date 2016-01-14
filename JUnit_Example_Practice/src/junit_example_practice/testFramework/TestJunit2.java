/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit_example_practice.testFramework;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Arvin
 */
public class TestJunit2 {

    String message = "Rebort";
    MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testSalutationMessage() {
        System.out.println("Inside testSalutationMessage()");
        message = "Hi!" + "Rebort";
        assertEquals(message, messageUtil.salutationMessage());
    }

}
