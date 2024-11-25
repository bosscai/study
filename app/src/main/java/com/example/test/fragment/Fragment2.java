package com.example.test.fragment;

import android.util.Log;
import android.widget.Button;

import com.example.test.R;
import com.example.test.mvvm.fragment.BaseFragment;
import com.faw.nativelib.NativeLib;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/8/19
 * describe:
 **/
public class Fragment2 extends BaseFragment {

    public static final String TAG = "Fragment2";

    private Button mBtnShow;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_layout_2;
    }

    @Override
    protected void initView() {
        mBtnShow = getRootView().findViewById(R.id.btn_show);
        mBtnShow.setOnClickListener(view -> {
            NativeLib nativeLib = new NativeLib();
            Log.e(TAG, "initView: " + nativeLib.stringFromJNI());
        });
    }

    @Override
    protected void initData() {

    }
}
