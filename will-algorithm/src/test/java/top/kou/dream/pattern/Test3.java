package top.kou.dream.pattern;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by ZXL on 2017/8/1.
 */
public class Test3 {
    private static final ForkJoinPool pool = new ForkJoinPool();

    static class MyRecursiveTask extends RecursiveTask<Integer> {

        @Override
        protected Integer compute() {
            return Integer.MAX_VALUE;
        }
    }

    static class MyCallable implements Callable<Integer> {
        Integer parameter;

        MyCallable(Integer parameter) {
            this.parameter = parameter;
        }

        @Override
        public Integer call() throws Exception {
            if (parameter == 50) {
                throw new RuntimeException("GOD IS GONE");
            }

            TimeUnit.MILLISECONDS.sleep(parameter);

            return parameter;
        }
    }

    @Test
    public void test() {
        MyRecursiveTask task = new MyRecursiveTask();


    }

    @Test
    public void test2() {
        Queue<Object> queue = new LinkedList<Object>();
        queue.offer(new Object());
        queue.add(new Object());
        System.out.print(queue.size());
    }
}
