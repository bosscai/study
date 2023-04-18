package com.example.test.danmaku;

import androidx.annotation.NonNull;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/16 08:09
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public interface Pool<T> {
    //从池中获取对象
    T acquire();
    //释放对象
    boolean release(@NonNull T instance);
}
