package top.kou.dream.parallel;

import java.util.concurrent.TimeUnit;

/**
 * Created by ZXL on 2017/8/7.
 */
public class Test4 {
    static class God {
        synchronized void m1() {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("m1 - " + Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized void m2() {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("m2 - " + Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        God god = new God();

        new Thread(){
            @Override
            public void run() {
               while (true) {
                   god.m1();
               }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true) {
                    god.m2();
                }
            }
        }.start();
    }

}
