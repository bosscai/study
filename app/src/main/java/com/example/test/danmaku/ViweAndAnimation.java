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
public class ViweAndAnimation implements ValueAnimator.AnimatorUpdateListener {

    public static final String TAG = "ViweAndAnimation";

    public View childView;
    public ValueAnimator animation;

    private int laneWidth;
    //放在第几个泳道，
    public int laneIndex = 2;

    public ViweAndAnimation(int getMeasuredWidth) {
        this.laneWidth = getMeasuredWidth;
        this.animation = ValueAnimator.ofFloat(1.0f);
        animation.setDuration(5000L);
        animation.setInterpolator(new LinearInterpolator());
        animation.addUpdateListener(this);
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画完成后，去除对应的View，避免内存泄漏
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

    public void pause() {
        animation.pause();
    }

    public void resume() {
        animation.resume();
    }

    public void stop() {
        animation.cancel();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float animatedFraction = animation.getAnimatedFraction();
        int left = (int) (laneWidth - animatedFraction * (laneWidth + childView.getMeasuredWidth()));
        childView.layout(left,  100 * laneIndex,
                left + childView.getMeasuredWidth(),
                childView.getMeasuredHeight() + 100 * laneIndex);
        Log.e(TAG, "right: " + childView.getRight() + " left: " + childView.getLeft() + " bottom: " + childView.getBottom() + " top: " + childView.getTop());
    }
}
