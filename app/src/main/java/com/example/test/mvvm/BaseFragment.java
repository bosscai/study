package com.example.test.mvvm;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test.R;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/5/10 11:18
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public abstract class BaseFragment extends Fragment {

    public static final String TAG = "BaseFragment";

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(setLayoutId(), container, false);
        initView();
        return mRootView;
    }

    protected abstract int setLayoutId();

    protected <T extends View> T initChildViewById(int id){
        if (mRootView == null){
            throw new IllegalStateException("mRootView is null");
        } else {
            return mRootView.findViewById(id);
        }
    }

    protected abstract void initView();
}
