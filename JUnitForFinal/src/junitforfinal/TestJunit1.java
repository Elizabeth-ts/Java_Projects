/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junitforfinal;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Arvin
 */
public class TestJunit1 {

    private final String message = "Robert";
    private final MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testPrintMessage() {
        System.out.println("Inside testPrintMessage()");
        assertEquals(message, messageUtil.printMessage());
    }

}
