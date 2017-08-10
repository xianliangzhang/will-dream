package top.kou.dream.pattern;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZXL on 2017/8/3.
 */
public class Test6 {

    static class MyQueue<T> {
        T[] data;
        int capacity;
        int size = 0;

        MyQueue(int capacity) {
            assert capacity > 0;

            this.size = 0;
            this.capacity = capacity;
            data = (T[]) new Object[size];
        }

        void add(T t) {
            synchronized (this) {
                while (size == capacity) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                boolean needNotify = size == 0;
                Object[] newContainer = new Object[++size];
                if (data.length != 0) {
                    System.arraycopy(data, 0, newContainer, 1, data.length);
                }
                newContainer[0] = t;
                this.data = (T[]) newContainer;

                System.out.println(String.format("Add Data [%d, %s]", Thread.currentThread().getId(), Arrays.toString(data)));
                if (needNotify) {
                    notifyAll();
                }
            }
        }

        void remove() {
            synchronized (this) {
                while (size == 0) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                boolean needNotify = size == capacity;
                Object[] newContainer = new Object[--size];
                System.arraycopy(data, 0, newContainer, 0, newContainer.length);
                this.data = (T[]) newContainer;
                System.out.println(String.format("Remove Data [%d, %s]", Thread.currentThread().getId(), Arrays.toString(data)));

                if (needNotify) {
                    this.notifyAll();
                }
            }
        }
    }

    public static void main(String... args) {
        MyQueue<Integer> container = new MyQueue<>(1);

        Thread producer = new Thread() {
            public void run() {
                container.add(1);
                container.add(2);
                container.add(3);
            }
        };

        Thread consumer = new Thread() {
            public void run() {
                int i = 0;
                while (i++ < 10) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                        container.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        producer.start();
        consumer.start();
        System.out.println("OK");

    }
}
