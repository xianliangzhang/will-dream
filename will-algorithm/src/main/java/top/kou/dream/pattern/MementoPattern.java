package top.kou.dream.pattern;

/**
 * Created by ZXL on 2017/8/3.
 */
public class MementoPattern {
    class Memento {
        int state;

        Memento(int state) {
            this.state = state;
        }
    }

    class Originator {
        private int state;

        Memento exportMemento() {
            return new Memento(state);
        }

        void importMemento(Memento memento) {
            this.state = memento.state;
        }
    }
}
