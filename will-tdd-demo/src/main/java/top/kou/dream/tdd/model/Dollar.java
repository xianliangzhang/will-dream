package top.kou.dream.tdd.model;

import java.math.BigDecimal;

public class Dollar implements Currency<Dollar> {
    private BigDecimal amount = BigDecimal.ZERO;

    public Dollar(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public Dollar add(Dollar dollar) {
        return new Dollar(amount.add(dollar.amount));
    }

    @Override
    public Dollar sub(Dollar dollar) {
        return new Dollar(amount.subtract(dollar.amount));
    }

    @Override
    public Dollar mul(Dollar dollar) {
        return new Dollar(amount.multiply(dollar.amount));
    }

    @Override
    public Dollar div(Dollar dollar) {
        if (dollar.amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException();
        }
        return new Dollar(amount.divide(dollar.amount, 8, BigDecimal.ROUND_HALF_DOWN));
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Dollar)) {
            return false;
        }
        return amount.compareTo(((Dollar) obj).amount) == 0;
    }
}
