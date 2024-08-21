package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkStateReceiver extends BroadcastReceiver {

    private static final String TAG = "NetworkStateReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiNetworkInfo.isConnected() || dataNetworkInfo.isConnected()) {
               //有网络连接
            }
        } else {
            //API大于23时使用下面的方式进行网络监听
            //获得ConnectivityManager对象
            /**
             * 情况说明：
             * 1、只开关移动数据，MOBILE connect is true/false
             * 2、只开关WiFi，WIFI connect is true/false
             * 3、WiFi打开时，开关移动数据，此时无反应
             * 4、移动数据打开时，开WiFi，此时先MOBILE connect is false，再WIFI connect is true
             * 关WiFi，此时先WIFI connect is false，再MOBILE connect is true
             */
            NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (networkInfo != null) {
                Log.d(TAG, "onReceive: " + networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
            } else {
                Log.d(TAG, "onReceive: networkInfo is null");
            }

        }
    }

}
