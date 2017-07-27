package top.kou.dream.maze.factory;

import top.kou.dream.maze.Maze;
import top.kou.dream.maze.component.Door;
import top.kou.dream.maze.component.Room;
import top.kou.dream.maze.component.Wall;

public class MazeFactory {
    public Maze makeMaze() {
        return new Maze();
    }

    public Wall makeWall() {
        return new Wall();
    }

    public Room makeRoom(int roomNo) {
        return new Room(roomNo);
    }

    public Door makeDoor(Room a, Room b) {
        return new Door(a, b);
    }
}
