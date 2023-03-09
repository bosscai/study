package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private Button btnEnable, btnDisable;
    private ImageView imgShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initView();

        btnEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgShow.setEnabled(true);
            }
        });

        btnDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgShow.setEnabled(false);
                imgShow.setImageResource(R.drawable.beauty_2);
            }
        });

        imgShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "可用", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        btnEnable = findViewById(R.id.btn_enable);
        btnDisable = findViewById(R.id.btn_disable);
        imgShow = findViewById(R.id.img_show);
    }
}