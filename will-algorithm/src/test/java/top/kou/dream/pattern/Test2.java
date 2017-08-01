package top.kou.dream.pattern;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by ZXL on 2017/8/1.
 */
public class Test2 {
    private static final ForkJoinPool pool = new ForkJoinPool();

    static class MyForkJoinTask extends ForkJoinTask<Integer> {

        @Override
        public Integer getRawResult() {
            return null;
        }

        @Override
        protected void setRawResult(Integer value) {

        }

        @Override
        protected boolean exec() {
            return false;
        }
    }

    static class MyRecursiveTask extends RecursiveTask<BigDecimal> {
        private Integer index = 0;
        private Integer lowerBound = 0;
        private Integer upperBound = 0;

        MyRecursiveTask(Integer index, Integer lowerBound, Integer upperBound) {
            this.index = index;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            System.out.println(String.format("%d [%d - %d]", index, lowerBound, upperBound));
        }

        @Override
        protected BigDecimal compute() {
            BigDecimal result = BigDecimal.ONE;
            for (Integer i= lowerBound; i <= upperBound; i++) {
                result = result.multiply(new BigDecimal(i));
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int index = 1;
        int lower = 1;
        int upper = 100;
        while (upper < Short.MAX_VALUE) {
            lower += 100;
            upper += 100;
        }
    }

}
