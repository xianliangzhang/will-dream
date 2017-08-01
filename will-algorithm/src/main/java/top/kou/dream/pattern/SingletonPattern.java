package top.kou.dream.pattern;

public class SingletonPattern {
    static class LazySingleton {
        private static LazySingleton instance = null;
        private LazySingleton() {}

        public static LazySingleton getInstance() {
            if (instance == null) {
                synchronized (SingletonPattern.class) {
                    if (instance == null) {
                        instance = new LazySingleton();
                    }
                }
            }
            return instance;
        }
    }

    static class HungrySingleton {
        private static final HungrySingleton instance = new HungrySingleton();
        private HungrySingleton() {}
        public static HungrySingleton getInstance() {
            return instance;
        }
    }

    static class GraceSingleton {
        String name = "GOD";
        private GraceSingleton() {}
        private static class Holder {
            private static final GraceSingleton instance = new GraceSingleton();
        }

        public static GraceSingleton getInstance() {
            return Holder.instance;
        }
    }

    public static void main(String[] args) {
        GraceSingleton graceSingleton = new GraceSingleton();
    }
}
