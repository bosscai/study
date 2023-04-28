package com.example.test.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.test.danmaku.Danmaku;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/16 09:05
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class DanmakuLayout extends ViewGroup {

    public static final String TAG = "DanmakuLayout";

    /**
     * viewQueue 盛放View弹幕View的队列
     * animatorQueue 盛放弹幕动画的队列
     * gapsQueue 盛放弹幕间间距的队列
     */
    private LinkedList<View> viewQueue = new LinkedList<>();
    private CopyOnWriteArrayList<Animator> animatorQueue = new CopyOnWriteArrayList<>();

    private LinkedList<Danmaku> danmakuQueue = new LinkedList<>();

    public DanmakuLayout(Context context) {
        super(context);
    }

    public DanmakuLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DanmakuLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "getMeasuredWidth: " + getMeasuredWidth());
    }

    /**
     * 规定的两条弹幕最小间距
     */
    public int minGap = 100;

    private OnLayoutChangeListener layoutChangeListener = new OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (MeasureSpec.getSize(getMeasuredWidth()) - right > minGap) {
                v.removeOnLayoutChangeListener(this);
                showNext();
                Log.e(TAG, "minGap: " + minGap);
            }
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量子布局
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "onMeasure: " + getMeasuredWidth());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "childCount: " + getChildCount() + " left: " + l + " top: " + t + " right: " + r + " bottom: " + b);
        Log.i(TAG, "onLayout: " + getMeasuredWidth());
    }

    /**
     * 添加弹幕数据
     */
    public void addDanmaku(Danmaku danmaku) {
        Log.i(TAG, "addItem: ");
        TextView textView = generateView(danmaku.text);
        danmakuQueue.addLast(danmaku);
        viewQueue.addLast(textView);
        addView(textView);
    }

    public TextView generateView(CharSequence text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        return textView;
    }

    /**
     * 展示下一条弹幕
     */
    private void showNext() {
        Log.i(TAG, "showNext: viewQueue.isEmpty: " + viewQueue.isEmpty());
        if (viewQueue.isEmpty() || danmakuQueue.isEmpty()) return;
        View curView = viewQueue.poll();
        Danmaku danmaku = danmakuQueue.poll();
        curView.addOnLayoutChangeListener(layoutChangeListener);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f);
        int curViewWidth = curView.getMeasuredWidth();
        /***
         * 这是上一个它前一个弹幕的长度
         * 1、如果当前弹幕的长度小于下一个弹幕的长度，这个时候需要计算一个新的Gap
         * 2、如果当前弹幕的长度大于上一个弹幕的长度，这个时候设置一个最小的Gap
         */
        if (!danmakuQueue.isEmpty()){
            View nextView = viewQueue.peek();
            int nextViewWidth = nextView.getMeasuredWidth();
            if (curViewWidth < nextViewWidth){
                //如果当前的弹幕小于下一条弹幕，需要设置一下两条弹幕的间距
                int curGap = nextViewWidth - curViewWidth;
                minGap = Math.max(minGap, curGap);
            } else {
                //如果当前的弹幕大于下一条弹幕，需要设置一下两条弹幕的间距
                minGap = 100;
            }
        }
        valueAnimator.setDuration(5000L);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                float left = (getMeasuredWidth() - fraction * (getMeasuredWidth() + curView.getMeasuredWidth()));
                curView.layout((int)left, curView.getTop(), (int) left + curView.getMeasuredWidth(), curView.getTop() + curView.getMeasuredWidth());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewQueue.addLast(curView);
                animatorQueue.remove(animation);
            }
        });
        valueAnimator.start();
    }

    public void start() {
        Log.e(TAG, "start: ");
        showNext();
    }

    public void stop() {
        Log.e(TAG, "stop: ");
        for (Animator animation : animatorQueue) {
            animation.end();
        }
    }

    public void pause() {
        Log.e(TAG, "pause: ");
        for (Animator animation : animatorQueue) {
            animation.pause();
        }
    }

    public void resume() {
        Log.e(TAG, "resume: ");
        for (Animator animation : animatorQueue) {
            animation.resume();
        }
    }
}
