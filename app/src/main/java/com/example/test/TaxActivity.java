package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TaxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax);
        findViewById(R.id.img_first).setOnClickListener(view -> {
            Log.e("TAG", "onCreate: ");
            startActivity(new Intent(this, TaxActivity2.class));
        });
    }
}