package com.example.test.view;

import android.graphics.Paint;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2024/4/11 16:23
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class DanmakuItem {
    String text;
    float x; // X轴坐标
    float y; // Y轴坐标
    Paint paint; // 弹幕绘制的画笔
    float speed; // 弹幕的速度

    DanmakuItem(String text, float x, float y, float speed, Paint paint) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.paint = paint;
    }

    // 更新弹幕位置
    void update() {
        x -= speed;
    }

    // 检查弹幕是否已经完全离开屏幕
    boolean isOffScreen() {
        return (x + paint.measureText(text)) < 0;
    }
}
