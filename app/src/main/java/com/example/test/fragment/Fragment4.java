package com.example.test.fragment;

import android.widget.FrameLayout;

import com.example.test.R;
import com.example.test.SplashFragment2;
import com.example.test.mvvm.fragment.BaseFragment;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/8/19
 * describe:
 **/
public class Fragment4 extends BaseFragment {

    private FrameLayout frameLayout;
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_layout_4;
    }

    @Override
    protected void initView() {
        frameLayout = getRootView().findViewById(R.id.fra_container);
        getChildFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), new SplashFragment2(), "")
                .commitAllowingStateLoss();
    }

    @Override
    protected void initData() {

    }
}
