package top.kou.dream.maze;

import top.kou.dream.maze.component.Room;

import java.util.LinkedHashSet;
import java.util.Set;

public class Maze {
    private Set<Room> rooms = new LinkedHashSet<>();

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public Room lookupRoom(int number) {
        for (Room room : rooms) {
            if (room.getNumber() == number) {
                return room;
            }
        }
        return null;
    }
}
