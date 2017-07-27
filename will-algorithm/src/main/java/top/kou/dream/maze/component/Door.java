package top.kou.dream.maze.component;

public class Door implements MapSite {
    private Room room1;
    private Room room2;
    private boolean isOpen;

    public Door(Room one, Room two) {
        this.room1 = one;
        this.room2 = two;
    }

    @Override
    public void enter() {
        System.out.println(String.format("Door Enter..."));
    }
}
