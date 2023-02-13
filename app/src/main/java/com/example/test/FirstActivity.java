package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mTimerStart, mTimerCancel;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initView();
    }

    private void initView() {
        mTimerStart = findViewById(R.id.btn_timer_start);
        mTimerCancel = findViewById(R.id.btn_timer_cancel);
        mTimerStart.setOnClickListener(this);
        mTimerCancel.setOnClickListener(this);
        timer = new Timer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_timer_start:
                Toast.makeText(getApplicationContext(), "定时器将在5秒后开始", Toast.LENGTH_SHORT).show();
                timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "定时器开始", Toast.LENGTH_SHORT).show());
                    }
                };
                timer.schedule(timerTask, 5000);
                break;
            case R.id.btn_timer_cancel:
                Toast.makeText(getApplicationContext(), "定时器取消", Toast.LENGTH_SHORT).show();
                timerTask.cancel();
                break;
        }
    }
}