package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.test.adapter.MainRecyclerViewAdapter;
import com.example.test.mvvm.MVVMActivity;
import com.example.test.view.CustomedLayoutActivity;
import com.example.test.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainRecyclerViewAdapter.onClickListener {

    private RecyclerView recyclerView;
    private List<String> data;
    private MyBroadcastReceiver receiver;
    public static final String TAG = "MainActivity";

    public static final int PERMISSION_REQUEST_CODE = 100;

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
    }

    private void initData() {
        data = new ArrayList<>();
        data.add("广播");
        data.add("Activity的隐式启动");
        data.add("ViewStub学习");
        data.add("Enable");
        data.add("ScreenShot");
        data.add("CustomedLayout");
        data.add("MVVM");
        data.add("TabLayout");
        data.add("webView");
        data.add("AIDL");
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter(data);
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void onClick(int pos) {
        Toast.makeText(this, "这是第" + (pos + 1) + "个", Toast.LENGTH_SHORT).show();
        switch (pos) {
            case 0: {
                Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
                intent.setComponent(new ComponentName("com.example.test","com.example.test.MyBroadcastReceiver"));
                sendBroadcast(intent);
            }
            break;

            case 1:
                startActivity(new Intent(this, FirstActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, ThirdActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, ScreenShotActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, CustomedLayoutActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, MVVMActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, ComponentActivity.class));
                break;
            case 8:
                startActivity(new Intent(this, WebViewActivity.class));
                break;
            case 9:
                startActivity(new Intent(this, AIDLActivity.class));
                break;
        }
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
        unregisterReceiver(receiver);
    }
}