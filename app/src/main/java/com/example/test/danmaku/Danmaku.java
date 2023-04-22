package com.example.test.danmaku;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/19 07:26
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：这是一个简单弹幕类，纯纯文字
 */
public class Danmaku implements ValueAnimator.AnimatorUpdateListener {

    public static final String TAG = "Danmaku";

    public View childView;
    private ValueAnimator animation;

    public CharSequence text;

    //泳道的长度，这个需要外部传进来
    public int laneWidth;
    //放在第几个泳道，
    public int laneIndex = 0;

    public Danmaku(CharSequence text) {
        this.text = text;
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

    /**
     * 这里是弹幕滚动的动画
     * @param animation
     */
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float animatedFraction = animation.getAnimatedFraction();
        int left = (int) (laneWidth - animatedFraction * (laneWidth + childView.getMeasuredWidth()));
        childView.layout(left,  childView.getMeasuredHeight() * laneIndex,
                left + childView.getMeasuredWidth(),
                childView.getMeasuredHeight() + childView.getMeasuredHeight() * laneIndex);
    }
}
