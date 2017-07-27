package top.kou.dream.uml;

import java.util.Collection;
import java.util.HashSet;

public class Client {
    public final Window window;
    public final Collection<ScrollBar> scrollBars = new HashSet<>();

    public Client(Window window, Collection<ScrollBar> scrollBars) {
        this.window = window;
        this.scrollBars.addAll(scrollBars);
    }
}
