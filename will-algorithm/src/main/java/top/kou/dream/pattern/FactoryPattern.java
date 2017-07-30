package top.kou.dream.pattern;

public class FactoryPattern {

    static abstract class AbstractFactory {
        abstract Object createInstance();
    }

    static class MacUI extends AbstractFactory {

        @Override
        Object createInstance() {
            return new MacUI();
        }
    }

    static class WinUI extends AbstractFactory {

        @Override
        Object createInstance() {
            return new WinUI();
        }
    }

    public static void main(String[] args) {
        //AbstractFactory fa = new WinUI().createInstance();
    }
}
