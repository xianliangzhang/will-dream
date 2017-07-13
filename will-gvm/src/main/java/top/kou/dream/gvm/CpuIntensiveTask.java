package top.kou.dream.gvm;

/**
 * Created by ZXL on 2017/7/11.
 */
public class CpuIntensiveTask {

    static class DeadLockThread extends Thread {
        private int a, b, index;

        DeadLockThread(int a, int b, int i) {
            this.a = a;
            this.b = b;
            this.index = i;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(String.format("%d - %d", index, a+b));
                }
            }
        }


    }

    public static int test() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
            return x;
        }
    }
    void clinit() {

    }

    public static void main(String[] args) {

        System.out.println(test());
    }

}
