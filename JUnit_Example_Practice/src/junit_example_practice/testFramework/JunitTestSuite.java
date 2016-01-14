/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit_example_practice.testFramework;

import junit_example_practice.testFramework.TestJunit1;
import junit_example_practice.testFramework.TestJunit2;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Arvin
 */
// Junit Suite Test
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestJunit1.class, TestJunit2.class
})

public class JunitTestSuite {

}
