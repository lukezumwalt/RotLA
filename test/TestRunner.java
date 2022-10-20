package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class TestRunner {
    
    public static  void testRunner() {
        System.out.println("Running JUnit test suite.");
        JUnitCore junitCore = new JUnitCore();
        junitCore.addListener(new CustomExecutionListener());
        Result result = JUnitCore.runClasses(UnitTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("Successful: " + result.wasSuccessful() + " ran " + result.getRunCount() + " tests.");
    }

    public static void main(String[] args) {
        testRunner();
    }

}
