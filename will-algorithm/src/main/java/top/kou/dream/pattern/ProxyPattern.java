package top.kou.dream.pattern;

/**
 * Created by ZXL on 2017/8/1.
 */
public class ProxyPattern {
    interface Subject {
        void request();
    }

    static class RealSubject implements Subject {

        @Override
        public void request() {
            System.out.println("RealSubject - request");
        }
    }

    static class ProxySubject implements Subject {
        private RealSubject realSubject;
        ProxySubject(RealSubject realSubject) {
            this.realSubject = realSubject;
        }

        @Override
        public void request() {
            realSubject.request();
            System.out.println("ProxySubject - request ");
        }
    }
}
