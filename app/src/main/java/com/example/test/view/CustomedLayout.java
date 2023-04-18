package com.example.test.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.test.R;
import com.example.test.danmaku.ViweAndAnimation;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/16 09:05
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class CustomedLayout extends ViewGroup {

    public static final String TAG = "CustomedLayout";

//    ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f);

    private View childView;

    private boolean blockShow = false;

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
            if (MeasureSpec.getSize(getMeasuredWidth()) - right > minGap){
                blockShow = false;
                index++;
                start();
                Log.e(TAG, "释放下一条弹幕: ");
                showNext();
            }
        }
    };

    private void showNext() {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.e(TAG, "onMeasure widthSize: " +  widthSize + " heightSize:" + heightSize);
        //测量子布局
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int heightOffset = 0;
        Log.e(TAG, "childCount: " + childCount);
        Log.e(TAG, "left: " + l +" top: " + t + " right: " + r + " bottom: " + b);
        for (int i=0; i<childCount;i++){
            View childView = getChildAt(i);
            childView.addOnLayoutChangeListener(layoutChangeListener);

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            Log.e(TAG, "childWidth: " + MeasureSpec.getSize(childWidth) + " childHeight: " +
                    MeasureSpec.getSize(childHeight));
            childView.layout(r-10, t + heightOffset,
                    r-10 + childView.getMeasuredWidth(),
                    t + heightOffset + childView.getMeasuredHeight());
            heightOffset += childView.getMeasuredHeight();
        }
    }
    int index = 0;
    public void start() {
        Log.e(TAG, "start: ");
        if (blockShow) return;
        int childCount = getChildCount();
//        valueAnimator.setDuration(2000);
//        valueAnimator.setInterpolator(new LinearInterpolator());
        if (index >=3)return;
//        for (int i=0; i<childCount; i++){
        ViweAndAnimation viewAndAnimation = new ViweAndAnimation(getMeasuredWidth());
        if (viewAndAnimation.childView != null) {
            viewAndAnimation.childView.removeOnLayoutChangeListener(layoutChangeListener);
        }
        viewAndAnimation.childView = getChildAt(index);
        viewAndAnimation.childView.addOnLayoutChangeListener(layoutChangeListener);
//            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    float animatedFraction = animation.getAnimatedFraction();
//                    int left = (int) (getMeasuredWidth() - animatedFraction * (getMeasuredWidth()
//                            + viewAndAnimation.childView.getMeasuredWidth()));
//
//                    viewAndAnimation.childView.layout(left, viewAndAnimation.childView.getTop(),
//                            left + viewAndAnimation.childView.getMeasuredWidth(),
//                            childView.getTop() + childView.getMeasuredHeight());
//                }
//            });
//        }
//        valueAnimator.start();
        viewAndAnimation.start();
        blockShow = true;
    }

    public void stop() {
        Log.e(TAG, "pause: ");
//        if (valueAnimator.isStarted()){
//            valueAnimator.cancel();
//        }
    }

    public void pause() {
        Log.e(TAG, "pause: ");
//        if (valueAnimator.isRunning()){
//            valueAnimator.pause();
//        }
    }

    public void resume() {
        Log.e(TAG, "pause: ");
//        if (valueAnimator.isPaused()){
//            valueAnimator.resume();
//        }
    }
}
