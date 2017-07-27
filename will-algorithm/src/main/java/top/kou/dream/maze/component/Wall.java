package top.kou.dream.maze.component;

public class Wall implements MapSite {

    @Override
    public void enter() {
        System.out.println(String.format("Wall Enter..."));
    }
}
