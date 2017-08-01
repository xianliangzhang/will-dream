package top.kou.dream.pattern;

public class FactoryMethodPattern {

    static interface Product {
        Integer getProductId();
    }

    static abstract class Creator {
        abstract Product create(Integer productId);
    }

    static class MyDocument extends Creator implements Product {
        private Integer id;

        @Override
        Product create(Integer productId) {
            return new MyDocument();
        }

        @Override
        public Integer getProductId() {
            return id;
        }
    }

}
