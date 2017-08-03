package top.kou.dream.pattern;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ZXL on 2017/8/2.
 */
public class Test5 {
    List<Integer> container = new LinkedList<>(Arrays.asList(1, 2, 3));


    @Test
    public void test() {
        Iterator<Integer> itr = container.iterator();
        for (;itr.hasNext();) {
            System.out.println(itr.next());
        }
    }

    static class LongWorker implements Runnable {
        private int index;

        LongWorker(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            doWork();
        }

        private void doWork() {
            try {
                for (int i = 0; i < 100; i++) {
                    TimeUnit.MILLISECONDS.sleep(i);
                    System.out.println(String.format("%d - %d", index, i));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test1() {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(1);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.DAYS, queue, new ThreadPoolExecutor.DiscardOldestPolicy());

        List<Future<?>> futures = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            pool.submit(new LongWorker(i));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
