package top.kou.dream.pattern;

/**
 * Created by Hack on 2017/7/30.
 */
public class AdapterPattern {

    static class Adaptee {
        void specification() {
            System.out.println("Adaptee - specification");
        }
    }

    static class Target {
        void targetSpecification() {
            System.out.println("Target - targetSpecification");
        }
    }

    static class Adapter extends Target {
        private Adaptee adaptee;

        Adapter(Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        void targetSpecification() {
            System.out.println("Target - targetSpecification");
        }
    }

    public static void main(String[] args) {
        Target target = new Adapter(new Adaptee());
        target.targetSpecification();
    }

}
