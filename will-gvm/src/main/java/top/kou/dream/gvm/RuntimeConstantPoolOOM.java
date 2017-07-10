package top.kou.dream.gvm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ZXL on 2017/7/10.
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        List<Object> container = new ArrayList<>();

        int i = 0;
        while (true) {
            container.add(String.valueOf(i++).intern());
        }
    }
}
