package top.kou.dream.pattern;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZXL on 2017/8/4.
 */
public class Test7 {
   static class God {
       synchronized void m() {
           try {
               TimeUnit.MILLISECONDS.sleep(2000);
               System.out.println(String.format("Non-Static-Method-Invoked [tid=%d]", Thread.currentThread().getId()));
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

       synchronized static void m2() {
           try {
               TimeUnit.MILLISECONDS.sleep(2000);
               System.out.println(String.format("Static-Method-Invoked [tid=%d]", Thread.currentThread().getId()));
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
   }

    public static void main(String[] args) {
        God god = new God();
        God god2 = new God();

        new Thread() {
            public void run() {
                for (int i = 10; i < 13; i++) {
                    god.m();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                for (int i = 10; i < 20; i++) {
                    god2.m();
                }
            }
        }.start();
    }
}
