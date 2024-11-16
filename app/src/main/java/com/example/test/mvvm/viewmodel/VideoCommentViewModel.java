package com.example.test.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/10/13 16:10
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class VideoCommentViewModel extends ViewModel {

    private MutableLiveData<Boolean> mCommentAction = new MutableLiveData<>();

    public LiveData<Boolean> getCommentAction() {
        return mCommentAction;
    }

    private MutableLiveData<String> mStickyAction = new MutableLiveData<>();

    public LiveData<String> getStickyAction() {
        return mStickyAction;
    }

    public void sendStickyAction(String stickyText){
        mStickyAction.postValue(stickyText);
    }

    public void sendCommentOpenAction(){
        mCommentAction.postValue(true);
    }

    public void sendCommentCloseAction(){
        mCommentAction.postValue(false);
    }
}
