package top.kou.dream.gvm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZXL on 2017/7/10.
 */
public class HeapOOM {
    static class OOMObject {}

    public static void main(String[] args) {
        List<OOMObject> container = new LinkedList<>();

        while (true) {
            container.add(new OOMObject());
        }
    }
}
