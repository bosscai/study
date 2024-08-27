package com.example.test.mvvm.data;

import androidx.lifecycle.LiveData;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/5/11 10:44
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class DTLiveData<T> extends LiveData<T> {
    @Override
    public void postValue(T value) {
        super.postValue(value);
    }
}
