package top.kou.dream.pattern;

public abstract class AbstractFactory {
    abstract ScrollBar createScrollBar();
    abstract Window createWindow();

    static class WinAbstractFactory extends AbstractFactory {

        @Override
        ScrollBar createScrollBar() {
            return new WinScrollBar();
        }

        @Override
        Window createWindow() {
            return new Window();
        }
    }

    static class MacAbstractFactory extends AbstractFactory {

        @Override
        ScrollBar createScrollBar() {
            return new WinScrollBar();
        }

        @Override
        Window createWindow() {
            return new Window();
        }
    }

    static abstract class Widget {

    }

    static class ScrollBar extends Widget {

    }

    static class Window extends Widget {

    }

    static class MacScrollBar extends ScrollBar {

    }

    static class WinScrollBar extends ScrollBar {

    }

    static class Client {
        Window window;
        ScrollBar scrollBar;

        Client(AbstractFactory factory) {
            this.window = factory.createWindow();
            this.scrollBar = factory.createScrollBar();
        }
    }

    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        AbstractFactory factory = os.toLowerCase().contains("window") ? new WinAbstractFactory() : new MacAbstractFactory();
        new Client(factory);
    }


}
