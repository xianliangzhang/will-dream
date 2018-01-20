package top.kou.dream;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * Hello world!
 */
public class App {
    private static final Map<String, URLClassLoader> loaders = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine();
            try {
                Object result = execute(cmd.trim());
                System.out.println(result.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private static String execute(String cmd) throws Exception {
        validate(cmd);
        if (cmd.endsWith("jar")) {
            URLClassLoader loader = loadJar(cmd);
            return "Jar Loaded...";
        } else {
            Object result = newCallable(cmd).call();
            return result.toString();
        }
    }

    private static void validate(String cmd) {
        if (null == cmd || "".equalsIgnoreCase(cmd)) {
            throw new RuntimeException("Invalid Command ... ");
        }
        if ("bye".equalsIgnoreCase(cmd)) {
            System.out.println("Bye-Bye!");
            System.exit(0);
        }
    }

    private static Callable newCallable(String cmd) throws Exception {
        Class clazz = getClass(cmd);
        if (!isCallable(clazz)) {
            throw new RuntimeException("Not a callable...");
        }
        if (!hasDefaultConstructor(clazz)) {
            throw new RuntimeException("Need a constructor with no-args...");
        }
        return (Callable) clazz.newInstance();
    }

    private static Class getClass(String cmd) {
        Iterator<URLClassLoader> iterator = loaders.values().iterator();
        while (iterator.hasNext()) {
            try {
                return iterator.next().loadClass(cmd);
            } catch (ClassNotFoundException e) {
                if (!iterator.hasNext()) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException("Class Not Found: " + cmd);
    }

    private static boolean isCallable(Class clazz) {
        Class[] inters = clazz.getInterfaces();
        Optional<?> optional = Arrays.stream(inters).filter(inter -> inter == Callable.class).findFirst();
        return optional.isPresent();
    }

    private static boolean hasDefaultConstructor(Class clazz) throws Exception {
        Constructor constructor = clazz.getConstructor();
        return null != constructor;
    }

    private static URLClassLoader loadJar(String cmd) throws Exception {
        if (loaders.containsKey(cmd)) {
            return loaders.get(cmd);
        }

        File file = new File(cmd);
        URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()});
        loaders.put(cmd, loader);
        return loader;
    }
}
