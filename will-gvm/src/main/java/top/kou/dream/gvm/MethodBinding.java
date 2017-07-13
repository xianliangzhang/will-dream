package top.kou.dream.gvm;

/**
 * Created by ZXL on 2017/7/13.
 */
public class MethodBinding {
    static abstract class MultipleMethod {
        //abstract void test(char x);
        abstract void test(int x);
        abstract void test(long x);
        abstract void test(Object x);
    }

    static class MultipleMethodX extends MultipleMethod {



        @Override
        void test(int x) {
            System.out.println("x - int");
        }

        @Override
        void test(long x) {
            System.out.println("x - long");
        }

        @Override
        void test(Object x) {
            System.out.println("x - Object");
        }
    }

    static class MultipleMethodY extends MultipleMethod {



        @Override
        void test(int x) {
            System.out.println("y - int");
        }

        @Override
        void test(long x) {
            System.out.println("y - long");
        }

        @Override
        void test(Object x) {
            System.out.println("y - Object");
        }
    }

    public static void main(String[] args) {
        MultipleMethod x = new MultipleMethodX();
        x.test('a');

        x = new MultipleMethodY();
        x.test('a');
    }
}
