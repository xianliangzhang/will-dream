package top.kou.dream.pattern;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by ZXL on 2017/8/7.
 */
public class Test8 {

    static AtomicBoolean locked = new AtomicBoolean(true);

    public static void main(String[] args) {
        new Thread() {
            public void run() {
                synchronized (locked) {
                    if (locked.get()) {
                       try {
                           locked.wait();
                           System.out.println("got lock " + locked.get());
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                    }
                }
            }
        }.start();

        synchronized (locked) {
            locked.compareAndSet(true, false);
            locked.notifyAll();

            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println("unfetch lock .." + i);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
