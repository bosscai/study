package com.example.test.view;

import android.util.Log;
import android.view.View;

import com.example.test.danmaku.Danmaku;

import java.util.LinkedList;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/21 10:29
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：自定义泳道的类，为了让弹幕在不同行滑动，
 */
public class Yongdap implements View.OnLayoutChangeListener {

    public static final String TAG = "Yongdap";

    /**
     * 泳道的长度、高度（泳道的宽度）、泳道的编号
     */
    private int length;
    private int height;
    private int index;
    //弹幕之间间距，不能让弹幕碰撞，重叠
    int minGap = 100;

    private LinkedList<Danmaku> dataList = new LinkedList<>();

    public Yongdap(int length, int height, int index) {
        this.length = length;
//        this.height = height;
        this.index = index;
    }

    /**
     * 开启泳道中的弹幕
     */
    public void start(){
        Log.e(TAG, "start: ");
        showNext();
    }

    /**
     *
     */
    public void addDanmaku(Danmaku danmaku){
        dataList.addLast(danmaku);
    }


    /**
     * 弹幕之间的距离已经合适，不会发生重叠，可以展示下一条弹幕
     */
    private void showNext(){
        Log.e(TAG, "showNext: ");
        if (dataList.isEmpty()) return;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (length - right > minGap){
            Log.e(TAG, "onLayoutChange length - right: " + (length - right) );
            showNext();
        }
    }
}
