package top.kou.dream.gvm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ZXL on 2017/7/12.
 */
public class BadClassLoader {
    private static String CLASS_PATH_PREFIX = "D:\\lab\\will-dream\\will-gvm\\target\\classes\\";
    private static String CLASS_NAME = "top.kou.dream.gvm.BadClassLoader";

    public static void main(String[] args) throws Exception {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = CLASS_PATH_PREFIX.concat(name.replaceAll("\\.", "/")).concat(".class");
                    InputStream in = new FileInputStream(fileName);
                    byte[] data = new byte[in.available()];

                    return defineClass(name, data, 0, data.length);
                } catch (FileNotFoundException e) {
                    throw new ClassNotFoundException(name);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Object obj = loader.loadClass(CLASS_NAME).newInstance();
        System.out.println(obj.getClass());

        System.out.println(obj instanceof BadClassLoader);
    }
}
