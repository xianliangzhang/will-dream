package top.kou.dream.gvm;

/**
 * Created by ZXL on 2017/7/10.
 */
public class JavaVMStackOOM {
    private static int counter = 0;

    private void dontStop() {
        while (true) {

        }
    }

    public void stackLackByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                private int tid = counter++;

                @Override
                public void run() {
                    System.out.println("counter=" + counter);
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLackByThread();
    }


}
