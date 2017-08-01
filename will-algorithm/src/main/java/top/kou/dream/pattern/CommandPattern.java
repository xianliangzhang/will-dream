package top.kou.dream.pattern;

import java.util.*;

/**
 * Created by ZXL on 2017/8/1.
 */
public class CommandPattern {
    interface Command {
        void execute();
        void undo();
    }

    static class MenuItem {
        Command command;

        void setCommand(Command command) {
            this.command = command;
        }

        void click() {
            command.execute();
        }
    }

    static class Menu {
        Set<MenuItem> menuItems = new HashSet<>();

        void add(MenuItem menuItem) {
            this.menuItems.add(menuItem);
        }

        void click() {
            for (MenuItem item : menuItems) {
                item.click();
            }
        }
    }

    static class Application {
        Set<Menu> menus = new HashSet<>();
        Queue<Command> history = new LinkedList<>();

        void click(MenuItem menuItem) {
            menuItem.click();
            history.add(menuItem.command);
        }
    }

    public static void main(String[] args) {
        Application application = new Application();

        MenuItem menuItem = new MenuItem();
        menuItem.setCommand(new Command() {
            @Override
            public void execute() {
                System.out.print("COMMAND DOING");
            }
            @Override
            public void undo() {
                System.out.print("COMMAND UNDO");
            }
        });

        Menu menu = new Menu();
        menu.add(menuItem);

        application.click(menu.menuItems.iterator().next());



    }
}
