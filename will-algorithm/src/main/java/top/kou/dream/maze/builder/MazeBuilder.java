package top.kou.dream.maze.builder;

import top.kou.dream.maze.Maze;

public abstract class MazeBuilder {
    public abstract MazeBuilder buildMaze();
    public abstract MazeBuilder buildRoom(int number);
    public abstract MazeBuilder buildDoor(int roomNumber1, int roomNumber2);
    public abstract Maze getMaze();
}
