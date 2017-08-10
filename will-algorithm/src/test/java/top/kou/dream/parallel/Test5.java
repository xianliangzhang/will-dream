package top.kou.dream.parallel;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZXL on 2017/8/7.
 */
public class Test5 {
    interface Container<T> {
        void put(T cake);
        T take();
    }

    static abstract class AbstractContainer<T> implements Container<T> {
        protected int capacity;
        protected T[] container;
        protected int size;

        protected AbstractContainer(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            this.container = (T[]) new Object[capacity];
        }
    }

    static class Queue<T> extends AbstractContainer<T> {

        protected Queue(int capacity) {
            super(capacity);
        }

        @Override
        public synchronized void put(T cake) {
            while (size == capacity) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    ;
                }
            }

            for (int i = size - 1; i > 0; i--) {
                container[i+1] = container[i];
            }
            container[0] = cake;
            size++;

            notifyAll();
        }

        @Override
        public synchronized T take() {
            while (size == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    ;
                }
            }

            T cake = container[--size];
            notifyAll();
            return cake;
        }
    }

    static class Stack<T> extends AbstractContainer<T> {

        protected Stack(int capacity) {
            super(capacity);
        }

        @Override
        public synchronized void put(T cake) {
            while (size == capacity) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    ;
                }
            }

            container[++size] = cake;
            if (size == 1) {
                notifyAll();
            }
        }

        @Override
        public synchronized T take() {
            while (size == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    ;
                }
            }

            T cake = container[--size];
            if (size == capacity - 1) {
                notifyAll();
            }

            return cake;
        }
    }

    static class Baker implements Runnable {
        private static int counter;
        private String name;
        private Container<String> table;

        Baker(Container<String> table, String name) {
            this.table = table;
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep((int) (Math.random() * 10));
                    String cake = String.format("No.%d Made By %s", counter++, name);
                    table.put(cake);
                    System.out.println(cake);
                } catch (InterruptedException e) {
                    ;
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private String name;
        private Container<String> table;

        Consumer(Container<String> table, String name) {
            this.table = table;
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep((int) (Math.random() * 10));
                    String cake = table.take();
                    System.out.println(String.format("%s Eat By %s", cake, name));
                } catch (InterruptedException e) {
                    ;
                }
            }
        }
    }

    public static void main(String[] args) {
        Container<String> table = new Queue<>(1);

        new Thread(new Baker(table, "A")).start();
        new Thread(new Consumer(table, "A")).start();

    }
}