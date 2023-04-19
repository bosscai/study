package com.example.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.test.danmaku.ViweAndAnimation;

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

    public static final String TAG = "CustomedLayout";

    private LinkedList<View> viewQueue = new LinkedList<>();

    private List<ViweAndAnimation> animationList = new ArrayList<>();

    public DanmakuLayout(Context context) {
        super(context);
    }

    public DanmakuLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DanmakuLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "childCount: " + getChildCount() + " left: " + l + " top: " + t + " right: " + r + " bottom: " + b);
    }

    public void start() {
        Log.i(TAG, "start: ");
        TextView textView = new TextView(getContext());
        textView.setText("一二三四五六七八九十");
        TextView textView1 = new TextView(getContext());
        textView1.setText("一");
        viewQueue.addLast(textView1);
        viewQueue.addLast(textView);
        addView(textView1);
        addView(textView);
        showNext();
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
        if (viewQueue.isEmpty()) return;
        ViweAndAnimation viweAndAnimation = new ViweAndAnimation(getMeasuredWidth());
        viweAndAnimation.childView = viewQueue.poll();
        animationList.add(viweAndAnimation);
//        //当前的View的宽度
//        int curViewWidth = MeasureSpec.getSize(viweAndAnimation.childView.getMeasuredWidth());
//        //下一个View的宽度
//        if (!viewQueue.isEmpty()){
//            //下一个要出来的View
//            final View nextView = viewQueue.getFirst();
//            int nextViewWidth = MeasureSpec.getSize(nextView.getMeasuredWidth());
//            Log.e(TAG, "curViewWidth: " + curViewWidth +" nextViewWidth: " + nextViewWidth );
//            //当前的view要比后一个view要长
//            if (curViewWidth > nextView.getMeasuredWidth()){
//                int i = viweAndAnimation.childView.getMeasuredWidth() - nextView.getMeasuredWidth();
//                minGap = Math.max(minGap, MeasureSpec.getSize(i));
//            };
//        }
//        Log.e(TAG, "minGap: " + minGap);
        viweAndAnimation.childView.addOnLayoutChangeListener(layoutChangeListener);
        viweAndAnimation.start();
    }

    /**
     * 添加弹幕数据
     * @param view
     */
    public void addItem(View view){
        viewQueue.addLast(view);
        addView(view);
    }

    public void stop() {
        Log.e(TAG, "stop: ");
        for (ViweAndAnimation animation : animationList) {
            animation.stop();
        }
    }

    public void pause() {
        Log.e(TAG, "pause: ");
        for (ViweAndAnimation animation : animationList) {
            animation.pause();
        }
    }

    public void resume() {
        Log.e(TAG, "resume: ");
        for (ViweAndAnimation animation : animationList) {
            animation.resume();
        }
    }
}
