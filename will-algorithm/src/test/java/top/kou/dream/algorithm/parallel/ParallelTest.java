package top.kou.dream.algorithm.parallel;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class ParallelTest {

    static class CountDownTask implements Runnable {
        BigDecimal factor;
        final int index;
        final int counter;
        final CountDownLatch startLatch;
        final CountDownLatch finishLatch;

        CountDownTask(int index, BigDecimal factor, int counter, CountDownLatch startLatch, CountDownLatch finishLatch) {
            this.index = index;
            this.factor = factor;
            this.counter = counter;
            this.startLatch = startLatch;
            this.finishLatch = finishLatch;
        }

        public void run() {
            try {
                startLatch.await();
                for (int x = 0; x < counter; x++) {
                    factor = factor.multiply(factor);
                    System.out.println(String.format("Task - %d - %d Running", index, counter));
                }
                System.out.println(String.format("Task - %d Finished", index));
                finishLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class CycleBarrierTask implements Runnable {
        BigDecimal factor;
        final int index;
        final int counter;
        final CyclicBarrier barrier;

        CycleBarrierTask(int index, BigDecimal factor, int counter, CyclicBarrier barrier) {
            this.index = index;
            this.factor = factor;
            this.counter = counter;
            this.barrier = barrier;
        }

        public void run() {
            try {
                System.out.println(String.format("Task - %d wait...", index));
                barrier.await();
                System.out.println(String.format("Task - %d going...", index));
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int x = 0; x < counter; x++) {
                factor = factor.multiply(factor);
                System.out.println(String.format("Task - %d - %d Running", index, counter));
            }
            System.out.println(String.format("Task - %d Finished", index));
        }
    }

    @Test
    public void testCountDownLatch() {
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new CountDownTask(i, BigDecimal.valueOf(1.1), 20, startLatch, finishLatch)).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Main-CountLatch-Thread GO...");
            startLatch.countDown();

            finishLatch.await();
            System.out.println("Main-CountLatch-Thread Finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCycleBarrier() {
        CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("CycleBarrier is go...");
            }
        });

        for (int i = 0; i < 10; i++) {
            new Thread(new CycleBarrierTask(i, BigDecimal.valueOf(1.1), 20, barrier)).start();
        }

        System.out.println("Main-Thread-Running...");
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
