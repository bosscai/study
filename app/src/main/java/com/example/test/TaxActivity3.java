package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class TaxActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_tax);
        findViewById(R.id.img_second).setOnClickListener(view -> {
            Log.e("TAG", "onCreate: ");
            startActivity(new Intent(this, TaxActivity4.class));
        });
    }
}