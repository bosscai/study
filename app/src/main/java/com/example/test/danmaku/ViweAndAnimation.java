package com.example.test.danmaku;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/19 07:26
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class ViweAndAnimation {

    public View childView;
    public ValueAnimator animation;

    public ViweAndAnimation(int getMeasuredWidth) {
        this.animation = ValueAnimator.ofFloat(1.0f);
        animation.setDuration(2000L);
        animation.setInterpolator(new LinearInterpolator());
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                int left = (int) (getMeasuredWidth - animatedFraction * (getMeasuredWidth
                        + childView.getMeasuredWidth()));
                childView.layout(left, childView.getTop(),
                        left + childView.getMeasuredWidth(),
                        childView.getTop() + childView.getMeasuredHeight());
            }
        });
    }

    public void start(){
        animation.start();
    }
}
