/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit_example_practice.testFramework;

import junit.framework.TestCase;

/**
 *
 * @author Arvin
 */
public class JavaTest extends TestCase {

    protected int value1, value2;

    // assigning the values
    @Override
    protected void setUp() {
        value1 = 3;
        value2 = 3;
    }

    // test method to add two values
    public void testAdd() {
        double result = value1 + value2;
        assertTrue(result == 6);
    }

}
