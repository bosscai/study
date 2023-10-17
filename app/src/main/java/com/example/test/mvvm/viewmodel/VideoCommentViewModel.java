package com.example.test.mvvm.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.test.mvvm.model.*;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/10/13 16:10
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class VideoCommentViewModel extends ViewModel {

    private DTLiveData<Boolean> mCommentAction;

    public DTLiveData<Boolean> getCommentAction() {
        if (mCommentAction == null) {
            mCommentAction = new DTLiveData<Boolean>();
        }
        return mCommentAction;
    }

    public void sendCommentOpenAction(){
        mCommentAction.postValue(true);
    }

    public void sendCommentCloseAction(){
        mCommentAction.postValue(false);
    }
}
