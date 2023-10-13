package com.example.test.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/10/13 14:50
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class CircleImageView extends ImageView {

    private static final String TAG = "CircleImageView";

    private int measuredWidth, measuredHeight;
    private float mRadius;
    private Paint mPaint;
    private BitmapShader mBitmapShader;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = this.getMeasuredWidth();
        measuredHeight = this.getMeasuredHeight();
        mRadius = Math.min(measuredHeight, measuredWidth) / 2.0f;
        if (mBitmapShader == null) {
            initBitmapShader();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmapShader != null) {
            mPaint.setShader(mBitmapShader);
            canvas.drawCircle(measuredWidth / 2.0f,measuredHeight / 2.0f, mRadius, mPaint);
        }
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
    }

    private void initBitmapShader() {
        if (getDrawable() == null){
            Log.i(TAG, "initBitmapShader: getDrawable() == null");
            return;
        }
        BitmapDrawable drawable = (BitmapDrawable) getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        if (bitmap == null){
            Log.i(TAG, "initBitmapShader: bitmap == null");
            return;
        }
        if (bitmap.getWidth() == 0 || bitmap.getHeight() == 0
                || measuredHeight == 0 || measuredWidth == 0){
            Log.i(TAG, "initBitmapShader:  width or height is error");
            return;
        }
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scaleX = measuredWidth / (float)bitmap.getWidth();
        float scaleY = measuredHeight / (float)bitmap.getHeight();
        float scale = Math.max(scaleX, scaleY);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        mBitmapShader.setLocalMatrix(matrix);
    }
}
