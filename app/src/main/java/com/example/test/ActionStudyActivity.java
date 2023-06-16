package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class ActionStudyActivity extends AppCompatActivity {

    private static final String TAG = "ActionStudyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_study);
        Intent intent = getIntent();
        Uri data = intent.getData();
        Log.e(TAG, "onCreate: " + data );
    }
}