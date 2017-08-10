package top.kou.dream.parallel;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 哲学家就餐问题
 * Created by ZXL on 2017/8/7.
 */
public class Test2 {
    static class Chopstick {
        final String name;

        Chopstick(String name) {
            this.name = name;
        }
    }

    static class Philosopher extends Thread {
        enum State {EATING, THINKING}

        private State state;
        private final String name;
        private final Chopstick l;
        private final Chopstick r;
        private final int temper = 5; // 待待5秒

        Philosopher(String name, Chopstick l, Chopstick r) {
            this.state = State.THINKING;
            this.name = name;
            this.l = l;
            this.r = r;
        }

        void go() {
            synchronized (l) {
                synchronized (r) {
                    String m = "%s Eating with chopsticks [%s%s]";
                    System.out.println(String.format(m, name, l.name, r.name));
                }
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (state.equals(State.EATING)) { // 当前EATING
                        String m = "%s Finished Eating...";
                        System.out.println(String.format(m, name));
                        TimeUnit.SECONDS.sleep(temper);

                        state = State.THINKING;
                    } else {
                        synchronized (l) {
                            System.out.println(String.format("%s god chopstick [%s]", name, l.name));

                            synchronized (r) {
                                System.out.println(String.format("%s god chopstick [%s]", name, r.name));

                                String m = "%s Start Eating with [%s%s]";
                                System.out.println(String.format(m, name, l.name, r.name));
                                TimeUnit.SECONDS.sleep(temper);

                                state = State.EATING;
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Chopstick[] chopsticks = {
                new Chopstick("1"),
                new Chopstick("2"),
                new Chopstick("3"),
                new Chopstick("4"),
                new Chopstick("5")
        };

        Philosopher[] philosophers = {
                new Philosopher("A", chopsticks[0], chopsticks[1]),
                new Philosopher("B", chopsticks[1], chopsticks[2]),
                new Philosopher("C", chopsticks[2], chopsticks[3]),
                new Philosopher("D", chopsticks[3], chopsticks[4]),
                new Philosopher("E", chopsticks[4], chopsticks[1]),
        };

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
