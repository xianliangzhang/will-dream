package top.kou.dream.parallel;

/**
 * Created by ZXL on 2017/8/7.
 */
public class Test1 {
    static class UserThread extends Thread {
        private Gate gate;
        private String name;
        private String address;

        UserThread(Gate gate, String name, String address) {
            this.gate = gate;
            this.name = name;
            this.address = address;
        }

        @Override
        public void run() {
            while (true) {
                gate.pass(name, address);
            }
        }
    }

    static class Gate {
        private static int counter;
        private String name;
        private String address;

        void pass(String name, String address) {
            synchronized (this) {
                this.name = name;
                this.address = address;
                counter++;
            }

            check();
        }

        void check() {
            if (name.charAt(0) != address.charAt(0)) {
                String m = String.format("******* Broken [%d, %s, %s] *******", counter, name, address);
                System.out.println(m);
            }
        }
    }

    public static void main(String[] args) {
        Gate gate = new Gate();
        new UserThread(gate, "Alice", "Alaska").start();
        new UserThread(gate, "Bobby", "Brazil").start();
        new UserThread(gate, "Calien", "Canada").start();
    }
}
