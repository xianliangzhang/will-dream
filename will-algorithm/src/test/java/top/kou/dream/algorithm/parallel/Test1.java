package top.kou.dream.algorithm.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by ZXL on 2017/7/28.
 */
public class Test1 {
    static class Singleton {
        public static int a = getA();
        public static final Properties config = new Properties();

        private static int getA() {
            System.out.println("init a");
            System.out.println(config.containsKey("xxoo"));
            return 1;
        }

        public static void testExceptionInInitializerError() {
            System.out.println(config.containsKey("xxoo"));
        }
    }

    public static void main(String[] args) {
        Singleton.testExceptionInInitializerError();
    }
}
