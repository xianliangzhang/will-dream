package top.kou.dream.parallel;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZXL on 2017/8/8.
 */
public class Test9 {
    static class Request {
        private static int counter = 0;
        private int requestId;

        Request() {
            this.requestId = ++counter;
        }

        void execute() {
            System.out.println(Thread.currentThread().getName() + " executes " + requestId);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                ;
            }
        }
    }

    static class Channel {
        private static List<Request> requests = new LinkedList<>();
        private static Thread[]  workers;

        Channel(int threads) {
            if (threads <= 0) {
                throw new IllegalArgumentException("Threads Cannot Be Negative.");
            }

            this.workers  = new Thread[threads];
        }

        void init() {
            for (int i = 0; i < workers.length; i++) {
                workers[i] = new Thread(new Worker(this));
                workers[i].start();
            }
        }

        synchronized void put(Request request) {
            requests.add(request);
            System.out.println("Put Request [ "+ request.requestId +" ]");
            notifyAll();
        }

        synchronized Request take() {
            while (requests.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    ;
                }
            }

            Request request = requests.get(0);
            System.out.println("Take Request [ "+ request.requestId +" ]");
            return request;
        }
    }

    static class Worker implements Runnable {
        private Channel channel;

        Worker(Channel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            channel.take().execute();
        }
    }

    static class Producer implements Runnable {
        private Channel channel;

        Producer(Channel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(3));
                    channel.put(new Request());
                } catch (InterruptedException e) {
                    ;
                }
            }
        }
    }

    public static void main(String[] args) {
        Channel channel = new Channel(1);
        channel.init();

        new Thread(new Producer(channel)).start();
//        new Thread(new Producer(channel)).start();
//        new Thread(new Producer(channel)).start();
//        new Thread(new Producer(channel)).start();
//        new Thread(new Producer(channel)).start();
    }

}
