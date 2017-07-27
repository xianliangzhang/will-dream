package top.kou.dream.maze.builder;

import top.kou.dream.maze.Maze;
import top.kou.dream.maze.component.Room;
import top.kou.dream.maze.component.Wall;

public class CountingMazeBuilder extends SimpleMazeBuilder {
    private Maze maze;
    private int doors;
    private int rooms;

    @Override
    public MazeBuilder buildMaze() {
        this.maze = new Maze();
        this.doors = 0;
        this.doors = 0;
        return this;
    }

    @Override
    public MazeBuilder buildRoom(int number) {
        super.buildRoom(number);
        this.rooms++;
        return this;
    }

    @Override
    public MazeBuilder buildDoor(int roomNumber1, int roomNumber2) {
        super.buildDoor(roomNumber1, roomNumber2);
        this.doors++;
        return this;
    }

    @Override
    public Maze getMaze() {
        return maze;
    }

    public int getRooms() {
        return rooms;
    }

    public int getDoors() {
        return doors;
    }
}
