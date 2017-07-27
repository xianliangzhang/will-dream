package top.kou.dream.maze.component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Room implements MapSite {
    public enum Direction {WEST, EAST, SOUTH, NORTH}

    private final int number;
    private Map<Direction, MapSite> mapSites = new HashMap<>();

    public Room(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setSide(Direction direction, MapSite mapSite) {
        mapSites.put(direction, mapSite);
    }

    public Map<Direction, MapSite> getMapSites() {
        return new HashMap<>(mapSites);
    }

    @Override
    public void enter() {
        System.out.println(String.format("Room Enter..."));
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Room && Objects.equals(number, ((Room) obj).number);
    }
}
