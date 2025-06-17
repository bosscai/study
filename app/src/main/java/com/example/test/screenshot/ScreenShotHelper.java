package com.example.test.screenshot;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Point;
//import android.media.MediaController2;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.security.auth.login.LoginException;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/13 18:08
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：截屏功能
 */
public class ScreenShotHelper {

    public static final String TAG = "ScreenShotHelper";

    /**
     * 读取媒体数据库时需要读取的列
     */
    private static final String[] MEDIA_PROJECTIONS = {
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.DATE_TAKEN,
            MediaStore.Images.ImageColumns.DATE_ADDED
    };


    /**
     * 读取媒体数据库时需要读取的列, 其中 WIDTH 和 HEIGHT 字段在 API 16 以后才有
     */
    private static final String[] MEDIA_PROJECTIONS_API_16 = {
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.DATE_TAKEN,
            MediaStore.Images.ImageColumns.DATE_ADDED,
            MediaStore.Images.ImageColumns.WIDTH,
            MediaStore.Images.ImageColumns.HEIGHT,
    };

    /**
     * 截屏依据中的路径判断关键字
     */
    private static final String[] KEYWORDS = {
            "screenshot", "screen_shot", "screen-shot", "screen shot",
            "screencapture", "screen_capture", "screen-capture", "screen capture",
            "screencap", "screen_cap", "screen-cap", "screen cap", "Screenshots", "Screenshot"
    };

    /**
     * 内部存储内容观察者,外部存储内容观察者
     */
    private ContentObserver mInternalObserver, mExternalObserver;

//    private ContentResolver mResolver;

    private onScreenShotListener mListener;

    private static Point sScreenRealSize;

    private long mStartListenerTime;

    /**
     * 已回调过的路径
     */
    private final static List<String> sHasCallbackPaths = new ArrayList<>();

    private Context mContext;

    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    /**
     * 实例化
     */
    public static ScreenShotHelper newInstance(Context context){
        assertInMainThread();
        return new ScreenShotHelper(context);
    }

    private ScreenShotHelper(Context context) {
        if (context == null){
            throw new IllegalArgumentException("The context must not be null");
        }

        mContext = context;
        //获取屏幕真实的分辨率
        if (sScreenRealSize == null){
            sScreenRealSize = getRealScreenSize();
            if (sScreenRealSize != null){
                Log.e(TAG, "sScreenRealSize.x: " + sScreenRealSize.x + " sScreenRealSize.y: " + sScreenRealSize.y);
            } else {
                Log.e(TAG, "get screen real size failed!");
            }
        }
    }

    private static void assertInMainThread() {
        if (Looper.getMainLooper() != Looper.myLooper()){
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            String methodMsg = null;
            if (elements.length >= 4){
                methodMsg = elements[3].toString();
            }
            throw new IllegalStateException("Call the method must be in main thread: " + methodMsg);
        }
    }

    /**
     * 或者屏幕的尺寸
     * @return
     */
    private Point getRealScreenSize() {
        Point screenSize = null;
        try {
            screenSize = new Point();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager == null){
                Log.e(TAG, "getRealScreenSize get WindowManager failed!");
                return null;
            } else {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                defaultDisplay.getRealSize(screenSize);
            }
        } catch (Exception e){
            Log.e(TAG, "getRealScreenSize Exception: " + e.getMessage());
        }
        return screenSize;
    }

    public void setListener(onScreenShotListener mListener) {
        this.mListener = mListener;
    }

    public onScreenShotListener getListener() {
        return mListener;
    }

    public void startListener(){
        Log.e(TAG, "startListener: ");
        assertInMainThread();
        //记录开始监听的时间戳
        mStartListenerTime = System.currentTimeMillis();
        //创建内容观察者
        mInternalObserver = new MediaContentObserver(mUiHandler, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        mExternalObserver = new MediaContentObserver(mUiHandler, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

//        mContext.getContentResolver().registerContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI,
//                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q, mInternalObserver);

//        mContext.getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q, mExternalObserver);
    }

    public void stopListener(){
        Log.e(TAG, "stopListener: ");
        assertInMainThread();
        if (mInternalObserver != null){
            try {
                mContext.getContentResolver().unregisterContentObserver(mInternalObserver);
            } catch (Exception e){
                Log.e(TAG, "stopListener: " + e.getMessage());
            }
            mInternalObserver = null;
        }
        if (mExternalObserver != null){
            try {
                mContext.getContentResolver().unregisterContentObserver(mExternalObserver);
            } catch (Exception e){
                Log.e(TAG, "stopListener: " + e.getMessage());
            }
            mExternalObserver = null;
        }
        mStartListenerTime = 0;
        if (mListener != null){
            mListener = null;
        }
    }

    /**
     * 处理媒体数据库的内容改变
     */
    private void handleMediaContentChange(Uri contentUri){
        Cursor cursor = null;
        try {
            //数据改变时查询数据库中最后加入的一条数据
//            Log.i(TAG, "handleMediaContentChange SDK_INT：" + Build.VERSION.SDK_INT);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                Bundle bundle = new Bundle();
//                bundle.putInt(ContentResolver.QUERY_ARG_LIMIT, 1);
//                cursor = mContext.getContentResolver().query(
//                        contentUri,
//                        Build.VERSION.SDK_INT < 16 ? MEDIA_PROJECTIONS : MEDIA_PROJECTIONS_API_16,
//                        bundle,
//                        null
//                );
//            } else {
                cursor = mContext.getContentResolver().query(
                        contentUri,
                        Build.VERSION.SDK_INT < 16 ? MEDIA_PROJECTIONS : MEDIA_PROJECTIONS_API_16,
                        null,
                        null,
                        MediaStore.Images.ImageColumns.DATE_ADDED + " desc limit 1"
                );
//            }
            if (cursor == null){
                Log.e(TAG, "handleMediaContentChange: " + "Deviant logic.");
            } else if (!cursor.moveToFirst()){
                Log.e(TAG, "handleMediaContentChange: " + "Cursor no data");
            } else {
                //获取各列的索引
                int dataIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                int dataTakenIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN);
                int widthIndex = -1;
                int heightIndex = -1;
                widthIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.WIDTH);
                heightIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.HEIGHT);
                //获取行数据
                String data = cursor.getString(dataIndex);
                long dateTaken = cursor.getLong(dataTakenIndex);
                int width = 0;
                int height = 0;
                if (widthIndex >= 0 && heightIndex >= 0){
                    width = cursor.getInt(widthIndex);
                    height = cursor.getInt(heightIndex);
                } else {
                    //API 16之前，宽高需要手动获取
                    Point size = getImageSize(data);
                    width = size.x;
                    height = size.y;
                }

                //处理获得到的第一行数据
                handleMediaRowData(data, dateTaken, width, height);
            }
        } catch (Exception e){
            Log.e(TAG, "handleMediaContentChange Exception: " + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
    }

    /**
     * 通过图片的路径返回图片的宽度和高度
     * @param imagePath
     * @return
     */
    private Point getImageSize(String imagePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        /**
         * 如果该值设为true那么将不返回实际的bitmap，也不给其分配内存空间这样就避免内存溢出了。
         * 但是允许我们查询图片的信息这其中就包括图片大小信息
         * options.outHeight (图片原始高度)和option.outWidth(图片原始宽度)
         */
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath);
        return new Point(options.outWidth, options.outHeight);
    }

    private void handleMediaRowData(String imagePath, long dateTaken, int width, int height) {
//        这个是啥，，如果代码跑不起来，需要搞清楚一下啊，
//        if (CommunityApplication.self.isComeBack()){
//            return;
//        }
        if (checkScreenShot(imagePath, dateTaken, width, height)){
            Log.e(TAG, "handleMediaRowData: path = " + imagePath + ", size = " + width +" * "
                    + height + ", date = " + dateTaken);
            if (mListener != null && !checkCallback(imagePath)){
                mListener.onShot(imagePath);
            }
        } else {
            // 如果在观察区间媒体数据库有数据改变，又不符合截屏规则，则输出到 log 待分析
            Log.w(TAG, "Media content changed, but not screenshot: path = " + imagePath
                    + "; size = " + width + " * " + height + "; date = " + dateTaken);
        }
    }

    /**
     * 判断是不是截屏
     * @param imagePath 图片的路径
     * @param dateTaken 时间戳
     * @param width     图片的宽度
     * @param height    图片高度
     * @return
     */
    private boolean checkScreenShot(String imagePath, long dateTaken, int width, int height) {
        /**
         * 判断依据一：时间判断
         * 如果加入数据库的时间在监听之前，或者与当前时间相差大于10秒，则认为当前没有截屏
         */
//        if (dateTaken > mStartListenerTime || (System.currentTimeMillis() - dateTaken) > 10_000){
//            Log.e(TAG, "dateTaken: " + dateTaken + " < mStartListenerTime: " + mStartListenerTime + " System.currentTimeMillis() - dateTaken " + (System.currentTimeMillis() - dateTaken));
//            Log.e(TAG, "currentTimeMillis: " + System.currentTimeMillis());
//            Log.e(TAG, "checkScreenShot: time is error！");
//            return false;

        if (dateTaken < mStartListenerTime) {
            Log.e(TAG, "dateTaken: " + dateTaken + "  mStartListenerTime: " + mStartListenerTime + "    " + (dateTaken < mStartListenerTime));
            Log.e(TAG, "currentTimeMillis: " + System.currentTimeMillis());
            Log.e(TAG, "checkScreenShot: time is error！");
            return false;
        }
        /**
         * 判断依据二：尺寸判断
         * 如果图片尺寸超过屏幕，则认为当前没有截屏
         */
        if (sScreenRealSize != null) {
            if (!((width <= sScreenRealSize.x && height <= sScreenRealSize.y)
                    || (height <= sScreenRealSize.x && width <= sScreenRealSize.y))) {
                Log.e(TAG, "checkScreenShot: size is error！");
                return false;
            }
        }

        /**
         * 判断依据三：路径判断
         * 判断图片路径是否含有指定的关键字之一，如果有则认为当前截屏了
         */
        if (TextUtils.isEmpty(imagePath)){
            Log.e(TAG, "checkScreenShot: imagePath is empty！");
            return false;
        }
        imagePath = imagePath.toLowerCase();
        for (String keyWork : KEYWORDS){
            if (imagePath.contains(keyWork)){
                return true;
            }
        }
        Log.e(TAG, "checkScreenShot: end");
        return false;
    }

    /**
     * 判断是否已回调过，某些手机ROM截屏一次会发出多次内容改变的通知；
     * 删除一个图片也会发通知，同时防止删除图片时误将上一张图片当做当前截屏
     * 大概缓存15~20条记录即可
     * @param imagePath 图片路径
     * @return
     */
    private boolean checkCallback(String imagePath){
        if (sHasCallbackPaths.contains(imagePath)){
            Log.e(TAG, "checkCallback: imagePath had done: " + imagePath);
            return true;
        }
        if (sHasCallbackPaths.size() >= 20){
            sHasCallbackPaths.subList(0, 5).clear();
        }
        sHasCallbackPaths.add(imagePath);
        return false;
    }


    public interface onScreenShotListener {

        void onShot(String path);
    }

    private class MediaContentObserver extends ContentObserver {

        public final static String TAG = "MediaContentObserver";

        private Uri mContentUri;
        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MediaContentObserver(Handler handler, Uri uri) {
            super(handler);
            mContentUri = uri;
        }

        @Override
        public void onChange(boolean selfChange) {
            Log.e(TAG, "onChange: " + selfChange);
            super.onChange(selfChange);
            handleMediaContentChange(mContentUri);
        }
    }

}
