package top.kou.dream.algorithm.parallel;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Hack on 2017/7/30.
 */
public class OperatorTest {

    @Test
    public void testAndOperator() {
        Boolean a = false;
        Boolean b = null;
        assert !a & b == null;
    }

    @Test
    public void testOrOperator() {
        Boolean a = false;
        Boolean b = null;
        assert a | (b == null);
    }

    @Test
    public void testXorOperator() {
        int x = 2;
        int y = 4;
        int xx = x|y;
        int yy = x^y;
        assert  xx==yy;
    }

}
