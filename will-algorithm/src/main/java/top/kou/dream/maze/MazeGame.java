package top.kou.dream.maze;

import top.kou.dream.maze.builder.MazeBuilder;
import top.kou.dream.maze.component.Door;
import top.kou.dream.maze.component.Room;
import top.kou.dream.maze.factory.MazeFactory;

public class MazeGame {
    private static Maze createMaze(MazeFactory factory) {
        Maze maze = new Maze();

        Room one = factory.makeRoom(1);
        Room two = factory.makeRoom(2);
        Door door = factory.makeDoor(one, two);

        maze.addRoom(one);
        maze.addRoom(two);

        return maze;
    }

    private static Maze createMaze(MazeBuilder builder) {
        return builder.buildMaze().buildRoom(1).buildRoom(2).buildDoor(1, 2).getMaze();
    }

    public static void main(String[] args) {

    }
}
