/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit_inclass_exercise.test;

import java.util.ArrayList;
import java.util.Arrays;
import junit_inclass_exercise.Database;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Arvin
 */
public class DatabaseTest {

    @Test
    public void testIsEmptyPositive() {
        trace("Positive test start!");
        Database b = new Database(Arrays.asList(new String[]{"1","2"}));
        assertEquals("Result", true, b.isEmpty());
        trace("Positive test end!");
    }

    @Test
    public void testIsEmptyNegitive() {
        trace("Negitive test start!");
        Database b = new Database(new ArrayList<String>());
        assertEquals("Result", false, b.isEmpty());
        trace("Negitive test end!");
    }

    private void trace(String s) {
        System.out.println(s);
    }
}
