package com.example.test.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2024/3/12 20:55
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class SimpleWidgetProvider extends AppWidgetProvider {

    public static final String TAG = "SimpleWidgetProvider";

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(TAG, "onEnabled: ");
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(TAG, "onUpdate: " + appWidgetIds );
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(TAG, "onDisabled: ");
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(TAG, "onDeleted: ");
    }
}
