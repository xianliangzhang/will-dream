package top.kou.dream.parallel;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

/**
 * Created by ZXL on 2017/8/9.
 */
public class Test10 {
    static class Future {
        private Object result;
        private Exception e;

        Future submit(Callable<?> callable) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        result = callable.call();
                    } catch (Exception e) {
                        result = null;
                        Future.this.e = e;
                    } finally {
                        synchronized (Future.this) {
                            Future.this.notifyAll();
                        }
                    }
                }
            }.start();

            return this;
        }

        Object get() {
            synchronized (this) {
                while (result == null) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        ;
                    }
                }

                if (this.e != null) {
                    throw new RuntimeException(e);
                }
                return this.result;
            }
        }
    }

    public static void main(String[] args) {
        Callable<BigDecimal> callable = new Callable<BigDecimal>() {
            @Override
            public BigDecimal call() throws Exception {
                BigDecimal r = new BigDecimal(1.1F);

                for (int i = 0; i < 17; i++) {
                    r = r.multiply(r);
                    System.out.println(String.format("%d [ %s ]", i, r.toString().substring(0, 10)));
                }

                return r;
            }
        };

        Future future = new Future().submit(callable);
        System.out.println("call is back...");
        Object object = future.get();
        System.out.println(object.toString().substring(0, 10));
    }
}
