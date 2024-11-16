package com.example.test.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/5/10 11:14
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：ViewMode中处理主要的逻辑，其不知道View中的任何信息
 */
public class TestViewModel extends ViewModel {

    private MutableLiveData<String> mText = new MutableLiveData<>();

    public LiveData<String> getStr() {
        return mText;
    }

    public void setStr(String str) {
        mText.postValue(str);
    }
}
