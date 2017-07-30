package top.kou.dream.pattern;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class BuilderPattern {
    static class Person {
        String head;
        String body;
        String hands;
        String feet;
    }

    static abstract class AbstractBuilder {
        abstract AbstractBuilder buildHead();
        abstract AbstractBuilder buildBody();
        abstract AbstractBuilder buildHands();
        abstract AbstractBuilder buildFeet();
        abstract Person getPerson();
    }

    static class PersonBuilder extends AbstractBuilder {
        private Person person;

        PersonBuilder() {
            person = new Person();
        }

        @Override
        AbstractBuilder buildHead() {
            person.head = "BigHead";
            System.out.println("PersonBuild build head");
            return this;
        }

        @Override
        AbstractBuilder buildBody() {
            person.head = "Body";
            System.out.println("PersonBuild build body");
            return this;
        }

        @Override
        AbstractBuilder buildHands() {
            person.head = "Hands";
            System.out.println("PersonBuild build hands");
            return this;
        }

        @Override
        AbstractBuilder buildFeet() {
            person.head = "Feet";
            System.out.println("PersonBuild build feet");
            return this;
        }

        @Override
        Person getPerson() {
            return person;
        }
    }

    static class GodBuilder extends AbstractBuilder {
        private Person person = new Person();

        @Override
        AbstractBuilder buildHead() {
            person.head = "No-Head-But-Soul";
            return this;
        }

        @Override
        AbstractBuilder buildBody() {
            person.body = "No-Body";
            return this;
        }

        @Override
        AbstractBuilder buildHands() {
            person.hands = "No-Hands";
            return this;
        }

        @Override
        AbstractBuilder buildFeet() {
            person.feet = "No-Feet";
            return this;
        }

        @Override
        Person getPerson() {
            return null;
        }
    }

    static class PersonFactory {
        static Person buildPerson(AbstractBuilder builder) {
            return builder.buildHead().buildBody().buildHands().buildFeet().getPerson();
        }
    }

    public static void main(String[] args) {
        Person person = PersonFactory.buildPerson(new PersonBuilder());
        System.out.println(person);

        Person god = PersonFactory.buildPerson(new GodBuilder());
        System.out.println(god);
    }
}
