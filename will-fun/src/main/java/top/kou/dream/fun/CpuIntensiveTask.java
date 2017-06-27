package top.kou.dream.fun;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

/**
 * Created by ZXL on 2017/6/27.
 */
public class CpuIntensiveTask implements Callable<CpuIntensiveTask> {
    public static final BigDecimal DEFAULT_PARAMETER = BigDecimal.valueOf(1.1);
    public static final int DEFAULT_REPEAT_TIMES = 20;

    private BigDecimal parameter;
    private BigDecimal result;
    private int repeatTimes;
    private long startTime;
    private long endTime;
    private long duration;

    public CpuIntensiveTask() {
        this.repeatTimes = DEFAULT_REPEAT_TIMES;
        this.parameter = DEFAULT_PARAMETER;
        this.result = parameter;
        this.startTime = System.currentTimeMillis();
    }

    public CpuIntensiveTask(BigDecimal parameter, int repeatTimes) {
        this.repeatTimes = repeatTimes;
        this.parameter = parameter;
        this.result = parameter;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public CpuIntensiveTask call() throws Exception {
        for (int i = 0; i < repeatTimes; i++) {
            result = result.multiply(result);
        }
        this.endTime = System.currentTimeMillis();
        this.duration = endTime - startTime;
        System.out.println(String.format("Task-Finished [thread=%d, duration=%d]", Thread.currentThread().getId(), duration));
        return this;
    }

}
