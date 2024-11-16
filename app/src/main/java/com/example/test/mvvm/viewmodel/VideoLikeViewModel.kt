package com.example.test.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2024/1/5 17:45
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
class VideoLikeViewModel: ViewModel() {

    private val mLikeAction by lazy {
        MutableLiveData<Boolean>()
    }

    fun getLikeAction(): LiveData<Boolean> {
        return mLikeAction;
    }

    fun sendLikeAction() {
        mLikeAction.postValue(true)
    }

    fun sendDislikeAction() {
        mLikeAction.postValue(false)
    }

}