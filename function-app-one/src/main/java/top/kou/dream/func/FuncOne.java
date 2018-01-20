package top.kou.dream.func;

import java.util.UUID;
import java.util.concurrent.Callable;

public class FuncOne implements Callable<String> {
    private static int counter = 0;

    @Override
    public String call() throws Exception {
        return String.format("invoked: %d, RespCode: ", counter++, UUID.randomUUID().toString());
    }
}
