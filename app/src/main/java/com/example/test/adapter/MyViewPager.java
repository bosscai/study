package com.example.test.adapter;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/8/19
 * describe: 自定义ViewPager，可以开关过度动画
 **/
public class MyViewPager extends ViewPager {

    private boolean enableAnimation = false;
    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEnableAnimation(boolean enableAnimation) {
        this.enableAnimation = enableAnimation;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, enableAnimation);
    }

}
