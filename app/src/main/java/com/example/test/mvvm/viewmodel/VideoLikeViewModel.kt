package com.example.test.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.test.mvvm.data.DTLiveData

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2024/1/5 17:45
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
class VideoLikeViewModel: ViewModel() {

    private val mLikeAction by lazy {
        DTLiveData<Boolean>()
    }

    fun getLikeAction(): DTLiveData<Boolean> {
        return mLikeAction;
    }

    fun sendLikeAction() {
        mLikeAction.postValue(true)
    }

    fun sendDislikeAction() {
        mLikeAction.postValue(false)
    }

}