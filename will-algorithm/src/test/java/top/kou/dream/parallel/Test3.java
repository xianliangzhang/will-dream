package top.kou.dream.parallel;

/**
 * Created by ZXL on 2017/8/7.
 */
public class Test3 {
    static class Parent {
        synchronized String test() {
           return "parent";
        }
    }

    static class Son extends Parent {
        String test() {
            return "son";
        }
    }

    public static void main(String[] args) {
        Parent son = new Son();
        System.out.println(son.test());
    }
}
