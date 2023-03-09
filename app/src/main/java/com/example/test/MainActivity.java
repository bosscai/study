package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.test.adapter.MainRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainRecyclerViewAdapter.onClickListener {

    private RecyclerView recyclerView;
    private List<String> data;
    private MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

        registerReceiver(receiver, intentFilter);


        initData();
        initView();
    }

    private void initData() {
        data = new ArrayList<>();
        data.add("广播");
        data.add("Activity的隐式启动");
        data.add("ViewStub学习");
        data.add("Enable");
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
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.example.test","com.example.test.MyBroadcastReceiver"));
                sendBroadcast(intent);
            }
            break;

            case 1:{
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.TEST");
//                intent.addCategory("android.intent.category.TEST_1");
//                intent.addCategory("android.intent.category.TEST_2");
                startActivity(new Intent(this, FirstActivity.class));
            }
            break;

            case 2:
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, ThirdActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}