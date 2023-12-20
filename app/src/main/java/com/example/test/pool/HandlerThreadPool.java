package com.example.test.pool;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/12/19 11:33
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：HandlerThread的线程池
 */
public class HandlerThreadPool {

    private static final String TAG = "HandlerThreadPool";
    /**
     * 紧急优先级类型handler thread，使用场景：需要立即执行，且不耗时任务。
     */
    public static String TYPE_URGENT = "URGENT";
    /**
     * 普通优先级类型handler thread，使用场景：非耗时（如IO）任务。
     */
    public static String TYPE_NORMAL = "NORMAL";
    /**
     * 低优先级类型handler thread，使用场景：不需要立即执行，且比较耗时任务，如IO操作。
     */
    public static String TYPE_LOW = "LOW";
    private static final Map<String, HandlerThread> sThreadPool = new HashMap<>();

    /**
     * 获取handler thread。
     */
    @NonNull
    public static HandlerThread getHandlerThread(String type) {
        if (TextUtils.isEmpty(type)) {
            type = TYPE_NORMAL;
        }
        // 首先不加锁获取提升性能，如果获取不到，加锁生成新的thread，避免线程同步问题生产多个handler thread。
        HandlerThread handlerThread = sThreadPool.get(type);
        if (handlerThread == null) {
            synchronized (sThreadPool) {
                handlerThread = sThreadPool.get(type);
                if (handlerThread == null) {
                    handlerThread = newHandlerThread(type);
                    handlerThread.start();
                    sThreadPool.put(type, handlerThread);
                }
            }
        }
        return handlerThread;
    }

    /**
     * 创建handler线程，urgent使用default优先级，普通使用
     */
    private static HandlerThread newHandlerThread(String type) {
        Log.i(TAG, "newHandlerThread: type = " + type);
        int priority = Process.THREAD_PRIORITY_DEFAULT;
        if (TextUtils.equals(type, TYPE_LOW)) {
            priority = Process.THREAD_PRIORITY_BACKGROUND;
        }
        return new HandlerThread(type, priority);
    }

    /**
     * 获取handler thread的message looper。
     */
    public static Looper getLooper(String type) {
        HandlerThread handlerThread = getHandlerThread(type);
        return handlerThread.getLooper();
    }

    public static int getPoolSize(){
        return sThreadPool.size();
    }

    /**
     * 释放HandlerThread的资源
     */
    public static boolean quitSafety(String type){
        if (sThreadPool.isEmpty()){
            return true;
        }
        HandlerThread thread = sThreadPool.get(type);
        if (thread == null){
            return true;
        }
        return thread.quitSafely();
    }

    public static void onRelease() {
        sThreadPool.forEach((str, handlerThread) -> {
            handlerThread.quitSafely();
        });
        sThreadPool.clear();
    }




}
