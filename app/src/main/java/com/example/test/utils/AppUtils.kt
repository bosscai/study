package com.example.test.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

/**
 * 作者：蔡承轩（阿蔡）
 * 时间：2023/11/3 17:41
 * 邮箱：caichengxuan.ccx@alibaba-inc.com
 * 描述：
 */
object AppUtils {

    const val TAG = "AppUtils"

    fun checkPermission(act: Activity, permissions: Array<String>, request: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var checkRes = PackageManager.PERMISSION_GRANTED
            permissions.forEach {
                checkRes = ContextCompat.checkSelfPermission(act, it)
                if (checkRes != PackageManager.PERMISSION_GRANTED) {
                    return@forEach
                }
            }
            if (checkRes != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(act, permissions, request)
            }
        }
        return true;
    }

    /**
     * 在asset文件夹中获取数据
     */
    fun getStringFromAssets(context: Context, fileName: String): String{
        try {
            val inputReader = InputStreamReader(context.assets.open(fileName))
            val bufferedReader = BufferedReader(inputReader)
            var line: String?
            val stringBuilder = StringBuilder()
            while (bufferedReader.readLine().also { line = it} != null){
                if (stringBuilder.isNotEmpty()){
                    stringBuilder.append("\r\n")
                }
                stringBuilder.append(line)
            }
            return stringBuilder.toString()
        } catch (excption: Exception) {
            Log.e(TAG, "getStringFromAssets: "  + excption.message )
        }
        return ""
    }
}