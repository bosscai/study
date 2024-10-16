package com.example.test.fragment;

import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.test.R;
import com.example.test.mvvm.fragment.BaseFragment;
import com.example.test.utils.FileUtils;

import java.io.File;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/8/19
 * describe:
 **/
public class Fragment1 extends BaseFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_layout_1;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        File filesDir = getActivity().getApplication().getFilesDir();
        Log.e(TAG, "filesDir: " + filesDir.getAbsolutePath() + " exists: " + filesDir.exists());
        boolean ccx = FileUtils.mkdirCustomDir(getContext(), "ccx");

    }
}
