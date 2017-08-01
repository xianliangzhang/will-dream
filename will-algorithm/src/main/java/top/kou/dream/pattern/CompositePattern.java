package top.kou.dream.pattern;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZXL on 2017/7/31.
 */
public class CompositePattern {
    static abstract class Equipment {
        // 设备功耗
        abstract double power();
        // 价格
        abstract double price();
    }

    static abstract class ComponentEquipment extends Equipment {
        private List<Equipment> equipments = new LinkedList<>();

        @Override
        double power() {
            double r = 0F;
            for (Equipment equipment : equipments) {
                r += equipment.power();
            }
            return r;
        }

        @Override
        double price() {
            double r = 0F;
            for (Equipment equipment : equipments) {
                r += equipment.price();
            }
            return r;
        }

        protected void add(Equipment equipment) {
            this.equipments.add(equipment);
        }
        protected void remove(Equipment equipment) {
            this.equipments.remove(equipment);
        }
    }

    static class Cabinet extends ComponentEquipment {

        @Override
        double power() {
            return 2.4F;
        }

        @Override
        double price() {
            return 4.2F;
        }
    }

    static class Bus extends Equipment {

        @Override
        double power() {
            return 4.3F;
        }

        @Override
        double price() {
            return 3.5F;
        }
    }

    static class Floppy extends Equipment {

        @Override
        double power() {
            return 8.2F;
        }

        @Override
        double price() {
            return 3.6F;
        }
    }

    public static void main(String[] args) {
        Cabinet cabinet = new Cabinet();
        cabinet.add(new Bus());
        cabinet.add(new Floppy());
        cabinet.add(new Floppy());

        System.out.print(cabinet.power() + " - " + cabinet.price());
    }
}
