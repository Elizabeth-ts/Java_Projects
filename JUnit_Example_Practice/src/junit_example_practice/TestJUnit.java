/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit_example_practice;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Arvin
 */
public class TestJUnit {

    @Test
    public void testAdd() {
        String str = "JUnit is working fine";
        assertEquals("JUnit is working fine", str);
    }
}
