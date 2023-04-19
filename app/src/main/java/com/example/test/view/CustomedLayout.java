package com.example.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.test.danmaku.ViweAndAnimation;

import java.util.LinkedList;
import java.util.Random;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/16 09:05
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class CustomedLayout extends ViewGroup {

    public static final String TAG = "CustomedLayout";

    private LinkedList<View> viewQueue = new LinkedList<>();

    public CustomedLayout(Context context) {
        super(context);
    }

    public CustomedLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomedLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int minGap = 200;

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
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.e(TAG, "onMeasure widthSize: " + widthSize + " heightSize:" + heightSize);
        //测量子布局
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int heightOffset = 0;
        Log.e(TAG, "childCount: " + childCount);
        Log.e(TAG, "left: " + l + " top: " + t + " right: " + r + " bottom: " + b);
    }

    public void start() {
        Log.e(TAG, "start: ");
        for (int i = 0; i < 10; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(getRandomString(new Random().nextInt(20)));
            viewQueue.addLast(textView);
            addView(textView);
        }
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
        Log.e(TAG, "showNext: viewQueue.isEmpty: " + viewQueue.isEmpty());
        if (viewQueue.isEmpty()) return;
        ViweAndAnimation viweAndAnimation = new ViweAndAnimation(getMeasuredWidth());
        viweAndAnimation.childView = viewQueue.poll();
        //当前的View的宽度
        int measuredWidth = viweAndAnimation.childView.getMeasuredWidth();
        //下一个View的宽度
        if (!viewQueue.isEmpty()){
            final View lastView = viewQueue.getFirst();
            if (viweAndAnimation.childView.getMeasuredWidth() > lastView.getMeasuredWidth()){
                int i = viweAndAnimation.childView.getMeasuredWidth() - lastView.getMeasuredWidth();
                minGap = MeasureSpec.getSize(i);
                Log.e(TAG, "minGap: " + minGap);
            };
        }
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
        Log.e(TAG, "pause: ");
    }

    public void pause() {
        Log.e(TAG, "pause: ");
    }

    public void resume() {
        Log.e(TAG, "pause: ");
    }
}
