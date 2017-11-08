package top.kou.dream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest {


    @org.junit.Test(expected = RuntimeException.class)
    public void testX() {
        System.out.println("xx");
        throw new RuntimeException();
    }
}
