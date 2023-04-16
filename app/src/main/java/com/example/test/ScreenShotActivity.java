package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.database.ContentObserver;
import android.os.Bundle;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.Toast;

import com.example.test.screenshot.ScreenShotHelper;

public class ScreenShotActivity extends AppCompatActivity {

    ScreenShotHelper screenShotHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);
        screenShotHelper = ScreenShotHelper.newInstance(getApplicationContext());
        screenShotHelper.setListener(new ScreenShotHelper.onScreenShotListener() {
            @Override
            public void onShot(String path) {
                Toast.makeText(getApplicationContext(), "截屏了啊！", Toast.LENGTH_SHORT).show();
            }
        });
        screenShotHelper.startListener();
    }
}