package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.test.adapter.MainRecyclerViewAdapter;
import com.example.test.media.MediaActivity;
import com.example.test.model.MainItem;
import com.example.test.mvvm.MVVMActivity;
import com.example.test.view.CustomedLayoutActivity;
import com.example.test.webview.WebViewActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MainItem> data;
    public static final String TAG = "MainActivity";

    public static final int PERMISSION_REQUEST_CODE = 100;

    private NetworkStateReceiver networkStateReceiver;

    private MutableLiveData<Boolean> mInBandRing = new MutableLiveData<>();

    /**
     * 需要申请的权限
     */
    private String[] mPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    /**
     * 缺少的权限的List，未授权的权限放在这个List里面
     */
    List<String> mLackPermissionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

        initPermission();

        networkStateReceiver = new NetworkStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkStateReceiver, intentFilter);
        File filesDir = getApplication().getFilesDir();
        Log.e(TAG, "filesDir: " + filesDir.getAbsolutePath() + " exists: " + filesDir.exists());
        Log.e(TAG, "mkdir res: " + Boolean.TRUE.equals(mInBandRing));
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new MainItem("DexClassLoader", (position, title) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("ccx://com.faw.android/dexClassLoader"));
            startActivity(intent);
        }));
        data.add(new MainItem("Activity的启动", ((position, title) -> {
            startActivity(new Intent(this, FirstActivity.class));
        })));
        data.add(new MainItem("ViewStub学习", (position, title) -> {
            startActivity(new Intent(this, SecondActivity.class));
        }));
        data.add(new MainItem("Media", (position, title) -> {
            startActivity(new Intent(this, MediaActivity.class));
        }));
        data.add(new MainItem("ScreenShot", (position, title) -> {
            startActivity(new Intent(this, ScreenShotActivity.class));
        }));
        data.add(new MainItem("CustomedLayout", (position, title) -> {
            startActivity(new Intent(this, CustomedLayoutActivity.class));
        }));
        data.add(new MainItem("MVVM", (position, title) -> {
            startActivity(new Intent(this, MVVMActivity.class));
        }));
        data.add(new MainItem("TabLayout", ((position, title) -> {
            startActivity(new Intent(this, ComponentActivity.class));
        })));
        data.add(new MainItem("webView", ((position, title) -> {
            startActivity(new Intent(this, WebViewActivity.class));
        })));
        data.add(new MainItem("AIDL", ((position, title) -> {
            startActivity(new Intent(this, AIDLActivity.class));
        })));
        data.add(new MainItem("个税", ((position, title) -> {
            startActivity(new Intent(this, TaxActivity.class));
        })));
        data.add(new MainItem("个税1", ((position, title) -> {
            startActivity(new Intent(this, TaxActivity6.class));
        })));
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    private void initPermission() {
        mLackPermissionList.clear();
        for (String permission : mPermissions){
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                mLackPermissionList.add(permission);
            }
        }

        if (!mLackPermissionList.isEmpty()){
            ActivityCompat.requestPermissions(this, mPermissions, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {

                }
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }
}