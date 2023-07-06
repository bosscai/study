package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActionStudyActivity extends AppCompatActivity {

    private static final String TAG = "ActionStudyActivity";

    private Button mBtnStartVibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_study);
        Intent intent = getIntent();
        Uri data = intent.getData();
        Log.e(TAG, "onCreate: " + data );

        mBtnStartVibrator = findViewById(R.id.btn_start_vibrator);
        mBtnStartVibrator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator service = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                service.vibrate(3000L);
            }
        });
    }
}