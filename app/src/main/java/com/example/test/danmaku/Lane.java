package com.example.test.danmaku;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.LinkedList;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/16 09:48
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：弹幕泳道宽度
 */
public class Lane {

    public static final String TAG = "Lane";

    private int laneWidth;

    public Lane(int laneWidth) {
        this.laneWidth = laneWidth;
    }

    //弹幕视图队列
    private LinkedList<View> viewQueue = new LinkedList<>();
    private View currentView;
    //用于限制泳道内弹幕间距的布尔值
    private boolean blockShow = false;
    private int horizontalGap = 0;

    private boolean isEmpty = false;

    public boolean isEmpty() {
        return isEmpty;
    }

    //弹幕布局监听器
    private View.OnLayoutChangeListener layoutChangeListener = new View.OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                   int oldLeft, int oldTop, int oldRight, int oldBottom) {
            //只用当前一个弹幕滚动的足够远，才开启下一个弹幕的动画
            if (laneWidth - left > v.getMeasuredWidth() + horizontalGap){
                blockShow = false;
                showNext();
            }
        }
    };

    //开始该泳道中下一个弹幕的滚动
    public void showNext(){
        //还未到展示下一个弹幕，则直接返回
        if (blockShow) return;
        if (currentView != null){
            currentView.removeOnLayoutChangeListener(layoutChangeListener);
        }
        //从泳道队列中取出弹幕视图
        currentView = viewQueue.poll();
        isEmpty = false;
        currentView.addOnLayoutChangeListener(layoutChangeListener);
        //计算每个弹幕的动画时间
        int distance = laneWidth + currentView.getMeasuredWidth();
        float speed = laneWidth * 1.0f / 4000f;
        long duration = (long) (distance / speed);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                int left = (int) (laneWidth - animatedFraction * (laneWidth + currentView.getMeasuredWidth()));
                currentView.layout(left, currentView.getTop(), left + currentView.getMeasuredWidth(),
                        currentView.getTop() + currentView.getMeasuredHeight());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) { }

            @Override
            public void onAnimationEnd(Animator animation) {
                recycle(currentView);
            }

            @Override
            public void onAnimationCancel(Animator animation) { }

            @Override
            public void onAnimationRepeat(Animator animation) { }
        });
        //弹幕视图滚动开始
        valueAnimator.start();
        blockShow = true;
    }

    /**
     * 添加View
     * @param view
     */
    public void add(View view){
        viewQueue.addLast(view);
        showNext();
    }

    private void recycle(View view){
        Log.e(TAG, "recycle View :" + this);
    }
}
