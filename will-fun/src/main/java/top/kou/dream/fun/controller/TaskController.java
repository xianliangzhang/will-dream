package top.kou.dream.fun.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.kou.dream.fun.CpuIntensiveTask;
import top.kou.dream.fun.service.TaskService;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZXL on 2017/6/27.
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    private static final Logger RUN_LOG = Logger.getLogger(TaskController.class);
    private static final Integer DEFAULT_THREADS = Runtime.getRuntime().availableProcessors();
    private static final Integer DEFAULT_TASKS = 10;

    @Autowired
    private TaskService taskService;

    @ResponseBody
    @RequestMapping
    public String run(Integer tasks, Integer threads) {
        int taskCount = tasks == null || tasks <= 0 ? DEFAULT_TASKS : tasks;
        int threadCount = threads == null || threads <= 0 ? DEFAULT_THREADS : threads;

        ThreadPoolExecutor executor = initializeThreadPool(threadCount);
        try {
            long startTime = System.currentTimeMillis();
            process(taskCount, executor);
            long endTime = System.currentTimeMillis();

            executor.shutdown();
            return String.format("Success: [tasks=%d, threads=%d, time=%d]", taskCount, threadCount, endTime-startTime);

        } catch (Exception e) {
            return "Failed...";
        }

    }

    private ThreadPoolExecutor initializeThreadPool(int threads) {
        return new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    private void process(int taskCount, ThreadPoolExecutor executor) throws Exception {
        Set<Future<CpuIntensiveTask>> futures = new HashSet<>();
        for (int i = 0; i < taskCount; i++) {
            futures.add(executor.submit(new CpuIntensiveTask()));
        }

        for (Future<CpuIntensiveTask> future : futures) {
            future.get();
        }
    }
}
