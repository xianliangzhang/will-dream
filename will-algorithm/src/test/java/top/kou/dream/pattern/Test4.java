package top.kou.dream.pattern;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ZXL on 2017/8/2.
 */
public class Test4 {
    private final ExecutorService service = Executors.newWorkStealingPool();

    private <T> Collection<T> test0(Collection<? extends Callable<T>> tasks) throws Exception {
        List<Future<T>> futures = new LinkedList<>();
        for (Callable<T> callable : tasks) {
            service.submit(callable);
        }

        List<T> results = new LinkedList<T>();
        for (Future<T> future : futures) {
            results.add(future.get());
        }

        return results;
    }

    @Test
    public void test() {

    }

}
