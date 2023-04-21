package com.example.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.test.danmaku.Lane;
import com.example.test.danmaku.Danmaku;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/16 09:05
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class DanmakuLayout extends ViewGroup {

    public static final String TAG = "DanmakuLayout";

    private LinkedList<View> viewQueue = new LinkedList<>();

    private List<Danmaku> animationList = new ArrayList<>();

    private boolean blockShow = false;

    private Lane lane = new Lane(1080);

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

    public int minGap = 100;

    private OnLayoutChangeListener layoutChangeListener = new OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (MeasureSpec.getSize(getMeasuredWidth()) - right > minGap) {
                v.removeOnLayoutChangeListener(this);
                showNext();
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

    public void start() {
        Log.i(TAG, "start: ");
        showNext();
    }

    /**
     * 添加弹幕数据
     */
    public void addDanmaku(View view) {
        Log.i(TAG, "addItem: ");
        viewQueue.addLast(view);
        addView(view);
    }

    public void addDanmaku(int len) {
        Log.i(TAG, "addItem: ");
        for (int i=0; i<len; i++){
            TextView textView = generateView(len);
            addDanmaku(textView);
        }
    }

    public TextView generateView(int len) {
        TextView textView = new TextView(getContext());
        textView.setText(getRandomString(len));
        return textView;
    }

    public String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 展示下一条弹幕
     */
    private void showNext() {
        Log.i(TAG, "showNext: viewQueue.isEmpty: " + viewQueue.isEmpty());
        if (blockShow) return;
        if (viewQueue.isEmpty()) return;
        Danmaku danmaku = new Danmaku(getMeasuredWidth());
        danmaku.childView = viewQueue.poll();
//        viweAndAnimation.laneIndex = new Random().nextInt(20);
        animationList.add(danmaku);
        danmaku.childView.addOnLayoutChangeListener(layoutChangeListener);
        danmaku.start();
    }

    public void stop() {
        Log.e(TAG, "stop: ");
        for (Danmaku animation : animationList) {
            animation.stop();
        }
    }

    public void pause() {
        Log.e(TAG, "pause: ");
        for (Danmaku animation : animationList) {
            animation.pause();
        }
    }

    public void resume() {
        Log.e(TAG, "resume: ");
        for (Danmaku animation : animationList) {
            animation.resume();
        }
    }
}
