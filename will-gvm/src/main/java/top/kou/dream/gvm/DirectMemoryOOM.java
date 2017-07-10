package top.kou.dream.gvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by ZXL on 2017/7/10.
 */
public class DirectMemoryOOM {

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(1024*1024);
        }
    }

}
