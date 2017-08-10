package top.kou.dream.parallel;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZXL on 2017/8/8.
 */
public class Test6 {

    public static void main(String[] args) {
        Thread thread = new Thread() {
           @Override
           public void run() {
               try {
                   synchronized (this) {
                       wait();
                   }
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
        };

        thread.start();
        thread.interrupted();

        try {
            TimeUnit.SECONDS.sleep(3);
            thread.interrupt();
        } catch (InterruptedException e) {
            ;
        }
    }

}
