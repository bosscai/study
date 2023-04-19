package com.example.test.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.R;

public class CustomedLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStart, mBtnPause, mBtnResume, mBtnStop;

    private DanmakuLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customed_layout);
        mLayout = findViewById(R.id.custom_layout);

        mBtnStart = findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(this);

        mBtnPause = findViewById(R.id.btn_pause);
        mBtnPause.setOnClickListener(this);

        mBtnResume = findViewById(R.id.btn_resume);
        mBtnResume.setOnClickListener(this);

        mBtnStop = findViewById(R.id.btn_stop);
        mBtnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                startDanmu();
                break;
            case R.id.btn_pause:
                pauseDanmu();
                break;
            case R.id.btn_resume:
                resumeDanmu();
                break;
            case R.id.btn_stop:
                stopDanmu();
                break;
            default:
        }
    }

    private void startDanmu() {
        Toast.makeText(this, "开始弹幕", Toast.LENGTH_SHORT).show();
        mLayout.start();
    }

    private void pauseDanmu() {
        Toast.makeText(this, "暂停弹幕", Toast.LENGTH_SHORT).show();
        mLayout.pause();
    }

    private void resumeDanmu() {
        Toast.makeText(this, "恢复弹幕", Toast.LENGTH_SHORT).show();
        mLayout.resume();
    }

    private void stopDanmu() {
        Toast.makeText(this, "停止弹幕", Toast.LENGTH_SHORT).show();
        mLayout.stop();
    }


}