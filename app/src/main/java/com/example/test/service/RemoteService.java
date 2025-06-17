package com.example.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.test.AIDLActivity;
import com.example.test.IServer;

/**
 * author：  caichengxuan
 * email：   chengxuan_cai@163.com
 * time：    2024/6/13
 * describe: 这是在另一个进程中的service中，
 **/
public class RemoteService extends Service {

    IServer.Stub serverBinder = new IServer.Stub() {
        @Override
        public int startService(String s) throws RemoteException {
            Log.e(AIDLActivity.TAG, "startService: " + s );
            return 10086;
        }

        @Override
        public String stopService(int num) throws RemoteException {
            return "Stop";
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return serverBinder;
    }
}
