package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.test.service.RemoteService;

public class AIDLActivity extends AppCompatActivity {

    public static final String TAG = "AIDLActivity";

    private IServer iServer;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iServer = IServer.Stub.asInterface(iBinder);
            Log.e(TAG, "onServiceConnected: " + componentName );
            try {
                int code = iServer.startService("FAW_CCX");
                Log.e(TAG, "startService: " + code );
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG, "onServiceConnected: " + componentName );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlactivity);
        Intent intent = new Intent(this, RemoteService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}