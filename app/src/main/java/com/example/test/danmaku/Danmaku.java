package com.example.test.danmaku;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/19 07:26
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：这是一个简单弹幕类，纯纯文字
 */
public class Danmaku {

    public static final String TAG = "Danmaku";

//    public View childView;
    private ValueAnimator animation;

    public CharSequence text;

    //泳道的长度，这个需要外部传进来
    public int laneWidth;
    //放在第几个泳道，
    public int laneIndex = 0;

    public Danmaku(CharSequence text) {
        this.text = text;
    }
}
