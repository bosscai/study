package com.example.test.screenshot;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/4/13 20:31
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：存储内容观察者
 */
public class MediaContentObserver extends ContentObserver {

    public final static String TAG = "MediaContentObserver";

    private Uri mContentUri;
    private Context mContext;
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public MediaContentObserver(Handler handler, Uri uri, Context mContext) {
        super(handler);
        mContentUri = uri;
        this.mContext = mContext;
    }

    @Override
    public void onChange(boolean selfChange) {
        Log.e(TAG, "onChange: " + selfChange);

//        handleMediaContentChange(mContentUri);
    }

    /**
     * 处理媒体数据库的内容改变
     */
//    private void handleMediaContentChange(Uri contentUri){
//        Cursor cursor = null;
//        try {
//            //数据改变时查询数据库中最后加入的一条数据
//            cursor = mContext.getContentResolver().query(
//                    contentUri,
//                    Build.VERSION.SDK_INT < 16 ? MEDIA_PROJECTIONS : MEDIA_PROJECTIONS_API_16,
//                    null,
//                    null,
//                    MediaStore.Images.ImageColumns.DATE_ADDED + " desc limit 1"
//            );
//
//            if (cursor == null){
//                Log.e(TAG, "handleMediaContentChange: " + "Deviant logic.");
//            } else if (!cursor.moveToFirst()){
//                Log.e(TAG, "handleMediaContentChange: " + "Cursor no data");
//            } else {
//                //获取各列的索引
//                int dataIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//                int dataTakenIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN);
//                int widthIndex = -1;
//                int heightIndex = -1;
//                if (Build.VERSION.SDK_INT >= 16){
//                    widthIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.WIDTH);
//                    heightIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.HEIGHT);
//                }
//                //获取行数据
//                String data = cursor.getString(dataIndex);
//                long dateTaken = cursor.getLong(dataTakenIndex);
//                int width = 0;
//                int height = 0;
//                if (widthIndex >= 0 && heightIndex >= 0){
//                    width = cursor.getInt(widthIndex);
//                    height = cursor.getInt(heightIndex);
//                } else {
//                    //API 16之前，宽高需要手动获取
//                    Point size = getImageSize(data);
//                    width = size.x;
//                    height = size.y;
//                }
//
//                //处理获得到的第一行数据
//                handleMediaRowData(data, dateTaken, width, height);
//            }
//        } catch (Exception e){
//            Log.e(TAG, "handleMediaContentChange Exception: " + e.getMessage());
//        } finally {
//            if (cursor != null && !cursor.isClosed()){
//                cursor.close();
//            }
//        }
//    }
}
