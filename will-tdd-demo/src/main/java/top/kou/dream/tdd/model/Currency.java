package top.kou.dream.tdd.model;

import java.math.BigDecimal;

public interface Currency<T extends Currency> {
    BigDecimal getAmount();
    T add(T t);
    T sub(T t);
    T mul(T t);
    T div(T t);
}
