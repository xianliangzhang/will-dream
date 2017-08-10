package top.kou.dream.parallel;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZXL on 2017/8/8.
 */
public class Test8 {
    static class Request {
        final String message;

        Request(String m) {
            this.message = m;
        }
    }

    static class RequestHandler implements Runnable {
        private Request request;

        RequestHandler(Request request) {
            this.request = request;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < request.message.length(); i++) {
                    System.out.print(request.message.charAt(i));
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                ;
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new RequestHandler(new Request(UUID.randomUUID().toString()))).start();
        new Thread(new RequestHandler(new Request(UUID.randomUUID().toString()))).start();
        new Thread(new RequestHandler(new Request(UUID.randomUUID().toString()))).start();
        new Thread(new RequestHandler(new Request(UUID.randomUUID().toString()))).start();
        new Thread(new RequestHandler(new Request(UUID.randomUUID().toString()))).start();
    }

}
