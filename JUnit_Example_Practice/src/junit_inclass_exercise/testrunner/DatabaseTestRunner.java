/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit_inclass_exercise.testrunner;

import junit_inclass_exercise.test.DatabaseTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author Arvin
 */
public class DatabaseTestRunner {

    public static void main(String[] argv) {
        Result result = JUnitCore.runClasses(DatabaseTest.class);
        for(Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
    }
}
