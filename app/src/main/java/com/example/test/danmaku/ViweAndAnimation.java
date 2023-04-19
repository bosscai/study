package com.example.test.danmaku;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/19 07:26
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class ViweAndAnimation {

    public static final String TAG = "ViweAndAnimation";

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

        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewGroup parent = (ViewGroup) childView.getParent();
                if (parent != null){
                    parent.removeView(childView);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void start(){
        animation.start();
    }
}
