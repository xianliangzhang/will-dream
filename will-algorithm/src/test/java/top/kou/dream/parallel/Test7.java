package top.kou.dream.parallel;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZXL on 2017/8/8.
 */
public class Test7 {
    static class ReadWriteLock {
        private int readingReaders = 0;
        private int writingWriters = 0;
        private boolean preferWrite = false;

        synchronized int readLock() {
            while (writingWriters > 0 || preferWrite) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    ;
                }
            }

            return ++readingReaders;
        }

        synchronized int readUnlock() {
            if (readingReaders > 0) {
                readingReaders--;
                notifyAll();
            }
            return readingReaders;
        }

        synchronized int writeLock() {
            while (writingWriters > 0 || readingReaders > 0) {
                try {
                    preferWrite = true;
                    wait();
                } catch (InterruptedException e) {
                    ;
                }
            }
            return ++writingWriters;
        }

        synchronized int writeUnlock() {
            if (writingWriters > 0) {
                writingWriters--;
                preferWrite = false;
                notifyAll();
            }
            return writingWriters;
        }
    }

    static class Data {
        private char[] data;
        private ReadWriteLock lock = new ReadWriteLock();

        Data(int capacity) {
            data = new char[capacity];
        }

        void doWrite(char c) {
            int x = lock.writeLock();
            try {
                for (int i = 0, s = data.length; i < s; i++) {
                    data[i] = c;
                    slow();
                }

                System.out.println(x + " Writers Write " + c);
            } finally {
                lock.writeUnlock();
            }
        }

        String doRead() {
            int x = lock.readLock();
            try {
                char[] r = new char[data.length];
                for (int i = 0; i < data.length; i++) {
                    r[i] = data[i];
                }
                slow();

                String y = String.valueOf(r);
                System.out.println(x + " Readers Read " );

                return y;
            } finally {
                lock.readUnlock();
            }
        }

        private void slow() {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                ;
            }
        }
    }

    static class Writer implements Runnable {
        private Data data;

        Writer(Data data) {
            this.data = data;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep((int) Math.random() * 3);
                    data.doWrite((char) new Random().nextInt());
                } catch (InterruptedException e) {
                    ;
                }
            }
        }
    }

    static class Reader implements Runnable {
        private Data data;

        Reader(Data data) {
            this.data = data;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep((int) Math.random() * 5);
                    System.out.println(data.doRead());
                } catch (InterruptedException e) {
                    ;
                }
            }
        }
    }

    public static void main(String[] args) {
        Data data = new Data(10);

        new Thread(new Writer(data)).start();
        new Thread(new Writer(data)).start();

        new Thread(new Reader(data)).start();
        new Thread(new Reader(data)).start();
        new Thread(new Reader(data)).start();
        new Thread(new Reader(data)).start();
        new Thread(new Reader(data)).start();

    }

}
