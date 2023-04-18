package com.example.test.danmaku;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/16 08:04
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：弹幕的View
 * 功能：1、生成一个新的子控件
 *      2、为子控件绑定数据
 *      3、测量子控件
 *      4、将子控件添加到容器控件
 *      5、布局子控件
 *      6、开启子控件动画
 */
public class LaneView extends ViewGroup {

    public static final String TAG = "LaneView";

    //存放弹幕数据的列表
    private List<Object> datas = new ArrayList<>();

    SimplePool<View> pool = new SimplePool<View>(5);

    public LaneView(Context context) {
        super(context);
    }

    public LaneView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LaneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public void show() {
        post(() -> {
            //1、生成新子控件
            View childView = obtain();
            //2、为子控件绑定数据
            bindView(childView);
            //3、测量子控件
            measureChild(childView, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            //4、将子View添加到容器控件中
            addView(childView);
            //5、布局子View
            int left = getMeasuredWidth();
            int top = getRandomTop(getMeasuredHeight());
            childView.layout(left, left + childView.getMeasuredWidth(),
                    top, top + childView.getMeasuredHeight());

        });
    }

    private View obtain() {
        View acquire = pool.acquire();
        if (acquire == null){
            createView();
        }
        return acquire;
    }

    private void recycle(View view){
//        view.detach();
        boolean release = pool.release(view);
        Log.e(TAG, "recycle result " + release);
    }

    private View createView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.item_layout_main, null);
    }

    private void bindView(View view) {

    }

    public int verticalGap = 5;

    /**
     * 计算完以后还会把它居中
     * @param commentHeight
     * @return
     */
    private int getRandomTop (int commentHeight) {
        //计算布局泳道的可用高度
        int laneHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        //计算可用高度中最多能布局几条泳道
        int lanesCapacity = (laneHeight + verticalGap) / (commentHeight + verticalGap);
        //计算完可用高度布局完所有泳道后剩余的空间
        int extraPadding = laneHeight - commentHeight * lanesCapacity -
                verticalGap * commentHeight * (lanesCapacity - 1);
        //计算第一条泳道相对于容器控件的mTop的值
        int firstLaneTop = getPaddingTop() + extraPadding / 2;
        //计算泳道垂直方向的随机偏移量，相当于把弹幕随机分配到不同的泳道
        int random = new Random().nextInt(lanesCapacity);
        Log.e(TAG, "lanesCapacity: " + lanesCapacity + " lane num: " + random );
        int randomOffset = random * (commentHeight + verticalGap);
        return firstLaneTop + randomOffset;
    }
    
}
