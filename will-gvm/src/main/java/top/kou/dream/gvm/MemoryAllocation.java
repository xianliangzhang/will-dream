package top.kou.dream.gvm;

/**
 * 本程序用于测试内存空间分配
 * args: -verbose: gc -Xms20m -Xmx20m -Xmn10m -XX:+SurvivorRatio=8
 * Created by ZXL on 2017/7/11.
 */
public class MemoryAllocation {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2 * _1M];
        allocation2 = new byte[2 * _1M];
        allocation3 = new byte[2 * _1M];
        allocation4 = new byte[4 * _1M];
    }
}
