package com.example.test.mvvm.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.test.mvvm.model.*;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/5/10 11:14
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：ViewMode中处理主要的逻辑，其不知道View中的任何信息
 */
public class TestViewModel extends ViewModel {

    private DTLiveData<String> str;

    public DTLiveData<String> getStr() {
        if (str == null){
            str = new DTLiveData<String>();
        }
        return str;
    }
}
