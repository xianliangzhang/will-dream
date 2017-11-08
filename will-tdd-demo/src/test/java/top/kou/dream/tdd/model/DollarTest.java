package top.kou.dream.tdd.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DollarTest {
    @Test
    public void getAmount() throws Exception {
        assertEquals(new Dollar(BigDecimal.TEN).getAmount(), BigDecimal.TEN);
    }

    @Test
    public void add() throws Exception {
        Dollar ten = new Dollar(BigDecimal.TEN);
        Dollar twe = new Dollar(new BigDecimal("20"));
        Dollar thr = new Dollar(new BigDecimal("30"));
        assert ten.add(ten).equals(twe);
        assert !ten.add(ten).equals(thr);
    }

    @Test
    public void sub() throws Exception {
        Dollar ten = new Dollar(BigDecimal.TEN);
        assert ten.sub(ten).equals(new Dollar(BigDecimal.ZERO));
        assert !ten.add(ten).equals(new Dollar(BigDecimal.ONE));
    }

    @Test
    public void mul() throws Exception {
        Dollar zero = new Dollar(BigDecimal.ZERO);
        Dollar one = new Dollar(BigDecimal.ONE);
        Dollar ten = new Dollar(BigDecimal.TEN);

        assert zero.mul(one).equals(zero);
        assert one.mul(one).equals(one);
        assert !one.mul(one).equals(ten);
    }

    @Test
    public void div() throws Exception {
        Dollar zero = new Dollar(BigDecimal.ZERO);
        Dollar one = new Dollar(BigDecimal.ONE);
        Dollar two = new Dollar(new BigDecimal("2"));
        Dollar ten = new Dollar(BigDecimal.TEN);

        assert zero.div(one).equals(zero);
        assert two.div(one).equals(two);
        assert !ten.div(two).equals(one);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivZero() {
        Dollar zero = new Dollar(BigDecimal.ZERO);
        Dollar one = new Dollar(BigDecimal.ONE);

        one.div(zero);
    }

    @Test
    public void testHashCode() throws Exception {
        Dollar ten = new Dollar(BigDecimal.TEN);

        assert ten.hashCode() == BigDecimal.TEN.hashCode();
    }

    @Test
    public void testEquals() throws Exception {
        assert new Dollar(BigDecimal.ONE).equals(new Dollar(BigDecimal.ONE));
        assert !new Dollar(BigDecimal.ONE).equals(new Dollar(BigDecimal.ZERO));
    }

}