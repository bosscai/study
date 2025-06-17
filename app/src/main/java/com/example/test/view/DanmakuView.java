package com.example.test.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2024/4/11 16:00
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class DanmakuView extends View {

    private List<DanmakuItem> danmakuList = new ArrayList<>(); // 用于存储所有弹幕
    private Handler handler = new Handler(); // 用于定时更新UI
    private boolean mStop = false;

    public DanmakuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addDanmaku(String text, float speed, Paint paint) {
        // 添加一个新的弹幕，并设定其初始位置在View的最右边
        float initialX = getWidth();
        float yPosition = (float) (Math.random() * (getHeight() - paint.getTextSize())) + paint.getTextSize();
        DanmakuItem danmaku = new DanmakuItem(text, initialX, yPosition, speed, paint);
        danmakuList.add(danmaku);
    }

    private Runnable updateView = new Runnable() {
        @Override
        public void run() {
            // 更新弹幕位置
            if (!mStop) {
                for (int i = danmakuList.size() - 1; i >= 0; i--) {
                    DanmakuItem danmaku = danmakuList.get(i);
                    danmaku.update();
                    // 移除已经离开屏幕的弹幕
                    if (danmaku.isOffScreen()) {
                        danmakuList.remove(i);
                    }
                }
            }
            // 重绘视图
            invalidate();
            // 让这个Runnable定时运行
            handler.postDelayed(this, 0); // 约每秒60帧
        }
    };

    public void onStop() {
        mStop = true;
    }
    public void onResume() {
        mStop = false;
    }




    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        handler.post(updateView); // 当View被添加到窗口时开始动画
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(updateView); // 当View被窗口移除时停止动画
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制屏幕上的每个弹幕
        for (DanmakuItem danmaku : danmakuList) {
            canvas.drawText(danmaku.text, danmaku.x, danmaku.y, danmaku.paint);
        }
    }
}
