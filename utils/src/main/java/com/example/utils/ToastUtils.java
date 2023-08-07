package com.example.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/8/1 14:37
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
public class ToastUtils {

    public static void show(Context context, String msg) {
        if (context != null) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

}
