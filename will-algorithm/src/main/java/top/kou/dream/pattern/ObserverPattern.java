package top.kou.dream.pattern;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ZXL on 2017/8/3.
 */
public class ObserverPattern {
    static abstract class Subject {
        protected Set<Observer> observers = new HashSet<>();

        void register(Observer observer) {
            this.observers.add(observer);
        }

        abstract void inform();
    }

    static abstract class Observer {
        abstract void update(Object event);
    }

    static class UserModel extends Subject {
        String name;
        String email;
        Integer age;

        void update(String name, String email, Integer age) {
            this.name = name;
            this.email = email;
            this.age = age;
            System.out.println("UserModel Updated...");

            inform();
        }

        @Override
        void inform() {
            this.observers.forEach(o -> {
                o.update(this);
            });
        }
    }

    static class BlockBoard extends Observer {
        public void register(Subject subject) {
            subject.register(this);
        }

        @Override
        void update(Object event) {
            System.out.println(String.format("Received Update Request [event=%s]", event.getClass().getName()));
        }
    }

    public static void main(String[] args) {
        Subject subject1 = new UserModel();
        new BlockBoard().register(subject1);

        ((UserModel) subject1).update("x", "y", 3);

    }
}
