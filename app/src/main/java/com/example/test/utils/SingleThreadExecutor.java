package ltd.qisi.dialer.utils;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2025/1/24
 * describe: 单线程的线程池单例类
 **/
public class SingleThreadExecutor {

    public static final String TAG = "SingleThreadExecutor";

    // 私有的线程池实例
    private final ExecutorService executorService;

    // 私有构造函数，防止外部实例化
    private SingleThreadExecutor() {
        // 创建一个单线程的线程池
        this.executorService = Executors.newSingleThreadExecutor();
    }

    // 静态内部类，用于持有单例实例
    private static class SingletonHolder {
        private static final SingleThreadExecutor INSTANCE = new SingleThreadExecutor();
    }

    // 提供一个全局访问点获取实例
    public static SingleThreadExecutor getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // 提交任务到线程池
    public void submit(Runnable task, int awaitTimeout) {
        Log.i(TAG, "submit");
        executorService.submit(task);
        shutdown(awaitTimeout);
    }

    // 关闭线程池
    private void shutdown(int awaitTimeout) {
        Log.i(TAG, "shutdown");
        executorService.shutdown();
        try {
            // 等待所有任务完成，或者超时，或者当前线程被中断
            if (!executorService.awaitTermination(awaitTimeout, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            Log.i(TAG, "InterruptedException:" + e);
            executorService.shutdownNow();
        }
    }
}