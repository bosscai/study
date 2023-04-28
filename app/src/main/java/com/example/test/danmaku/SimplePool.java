package com.example.test.danmaku;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/16 08:11
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class SimplePool<T> implements Pool<T>{

    public static final String TAG = "SimplePool";

    //池对象容器
    private final Object[] mPool;
    //池大小
    private int mPoolSize = 0;

    public SimplePool(int maxSize) {
        if (maxSize <= 0){
            throw new IllegalArgumentException("The pool size must be > 0");
        }
        //构造池对象容器
        mPool = new Object[maxSize];
    }

    public int getPoolSize() {
        return mPoolSize;
    }

    @Override
    public T acquire() {
        if (mPoolSize > 0){
            //总是从池的末尾来读取对象
            final int lastPooledIndex = mPoolSize - 1;
            T instance = (T) mPool[lastPooledIndex];
            mPool[lastPooledIndex] = null;
            mPoolSize--;
            return instance;
        }
        return null;
    }

    /**
     * 释放对象并放入池
     */
    @Override
    public boolean release(@NonNull T instance) {
        if (isInPool(instance)) {
            Log.i(TAG, "release failed because of inPool!");
            return false;
        } else {
            if (mPoolSize < mPool.length){
                mPool[mPoolSize] = instance;
                mPoolSize++;
                return true;
            }
        }
        Log.i(TAG, "release failed because Pool is full!");
        return false;
    }

    private boolean isInPool(T instance) {
        for (int i=0; i<mPoolSize;i++){
            if (mPool[i] == instance){
                return true;
            }
        }
        return false;
    }
}
