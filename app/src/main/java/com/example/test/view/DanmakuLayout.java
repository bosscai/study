package com.example.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.test.danmaku.Danmaku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/16 09:05
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class DanmakuLayout extends ViewGroup {

    public static final String TAG = "DanmakuLayout";

    private LinkedList<View> viewQueue = new LinkedList<>();

    private LinkedList<Danmaku> danmakuQueue = new LinkedList<>();

    private List<Danmaku> animationList = new ArrayList<>();

    private boolean blockShow = false;

    private int maxLane = 3;

    private HashMap<Integer, Yongdap> laneMap;

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

    public void init(){
        laneMap = new HashMap<>(maxLane);
        for (int i=0; i<maxLane; i++){
            laneMap.put(i, new Yongdap(getMeasuredWidth(), 100, i));
        }
    }

    public void start() {
        Log.i(TAG, "start: ");
        showNext();
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

    /**
     * 添加弹幕数据
     */
    public void addDanmaku(List<Danmaku> danmakus) {
        Log.i(TAG, "addItems: ");
        for (int i=0; i<danmakus.size(); i++){
            laneMap.get(i).addDanmaku(danmakus.get(i));
        }
    }

    public TextView generateView(CharSequence text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        return textView;
    }


    public void show(Danmaku danmaku) {
//        post(new Runnable() {
//            @Override
//            public void run() {
                TextView childView = generateView(danmaku.text);
//                bindView(danmaku, childView);

        measureChild(childView, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        addView(childView);
        int left = getMeasuredWidth();
        int top = childView.getMeasuredHeight();
        childView.layout((int)(left * 0.5f), top, (int)(left * 0.5f) + childView.getMeasuredWidth(), top + childView.getMeasuredHeight());
//            }
//        });
//            /**
//             * put child view into [Lane]
//             */
//            laneMap[top]?.add(child, data) ?: run {
//                Lane(measuredWidth).also {
//                    it.add(child, data)
//                    laneMap[top] = it
//                    it.showNext()
//                }
//            }
//        }
    }

    /**
     * 展示下一条弹幕
     */
    private void showNext() {
        Log.i(TAG, "showNext: viewQueue.isEmpty: " + viewQueue.isEmpty());
        if (blockShow) return;
        if (viewQueue.isEmpty()) return;
        Danmaku danmaku = danmakuQueue.poll();
        danmaku.laneWidth = getMeasuredWidth();
        danmaku.childView = viewQueue.poll();
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
