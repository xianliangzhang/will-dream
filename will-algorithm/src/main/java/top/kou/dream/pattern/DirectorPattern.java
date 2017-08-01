package top.kou.dream.pattern;

import java.awt.*;

/**
 * 装饰器模式：要求装饰器与被装饰的对象拥有相同的接口，但装饰器可以改变被装饰对象的行为
 * Created by ZXL on 2017/8/1.
 */
public class DirectorPattern {

    static abstract class Component {
        abstract void draw();
        abstract void resize();
    }

    static abstract class Director extends Component {
        protected Component component;
        Director(Component component) {
            this.component = component;
        }
    }

    static class TextViewComponent extends Component {

        @Override
        void draw() {
            System.out.println("TextViewComponent drawing...");
        }

        @Override
        void resize() {
            System.out.println("TextViewComponent resizing...");
        }
    }

    static class BorderDirector extends Director {

        BorderDirector(Component component) {
            super(component);
        }

        @Override
        void draw() {
            this.component.draw();
            System.out.println("BorderDirector drawing border...");
        }

        @Override
        void resize() {
            this.component.resize();
            System.out.println("BorderDirector resizing...");
        }
    }

    static class ScrollableDirector extends Director {
        ScrollableDirector(Component component) {
            super(component);
        }

        @Override
        void draw() {
            this.component.draw();
            System.out.println("ScrollableDirector drawing border...");
        }

        @Override
        void resize() {
            this.component.resize();
            System.out.println("ScrollableDirector resizing...");
        }
    }

    public static void main(String[] args) {
        new ScrollableDirector(new BorderDirector(new TextViewComponent())).draw();
    }

}
