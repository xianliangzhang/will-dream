package top.kou.dream.maze.builder;

import top.kou.dream.maze.Maze;
import top.kou.dream.maze.component.Door;
import top.kou.dream.maze.component.MapSite;
import top.kou.dream.maze.component.Room;
import top.kou.dream.maze.component.Wall;

import java.util.Map;

public class SimpleMazeBuilder extends MazeBuilder {
    private Maze maze;

    @Override
    public MazeBuilder buildMaze() {
        this.maze = new Maze();
        return this;
    }

    @Override
    public MazeBuilder buildRoom(int number) {
        Room room = new Room(number);
        room.setSide(Room.Direction.EAST, new Wall());
        room.setSide(Room.Direction.WEST, new Wall());
        room.setSide(Room.Direction.NORTH, new Wall());
        room.setSide(Room.Direction.SOUTH, new Wall());
        maze.addRoom(room);
        return this;
    }

    @Override
    public MazeBuilder buildDoor(int roomNumber1, int roomNumber2) {
        if (maze != null) {
            Room one = maze.lookupRoom(roomNumber1);
            Room two = maze.lookupRoom(roomNumber2);
            Door door = new Door(one, two);

            for (Map.Entry<Room.Direction, MapSite> ae : one.getMapSites().entrySet()) {
                for (Map.Entry<Room.Direction, MapSite> be : two.getMapSites().entrySet()) {
                    if (ae.getValue().equals(be.getValue())) {
                        one.setSide(ae.getKey(), door);
                        two.setSide(be.getKey(), door);
                    }
                }
            }
        }
        return this;
    }

    @Override
    public Maze getMaze() {
        return null;
    }
}
