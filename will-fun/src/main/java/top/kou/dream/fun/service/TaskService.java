package top.kou.dream.fun.service;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * Created by ZXL on 2017/6/27.
 */
@Service
public class TaskService {
    private static final Logger RUN_LOG = Logger.getLogger(TaskService.class);
    private static final int processors = Runtime.getRuntime().availableProcessors();
    private static final ThreadPoolExecutor executor =
            new ThreadPoolExecutor(processors, processors, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new RejectedExecutionHandler() {

                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    RUN_LOG.error("  -- Reject --" + r);
                }
            });

    public <T> Future<T> submit(Callable<T> callable) {
        return executor.submit(callable);
    }
}
