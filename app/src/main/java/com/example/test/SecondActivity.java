package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

/**
 * 学习一下ViewStub
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SecondActivity";

    private ViewStub viewStub;
    private TextView hintText;
    private Button btnShow, btnChange, btnHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        viewStub = findViewById(R.id.vs_test);
        btnShow = findViewById(R.id.btn_show);
        btnChange = findViewById(R.id.btn_change);
        btnHide = findViewById(R.id.btn_hide);

        btnShow.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnHide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                try {
                    View iv_vsContent = viewStub.inflate();
                    hintText = iv_vsContent.findViewById(R.id.tv_vsContent);
                } catch (Exception e) {
                    viewStub.setVisibility(View.VISIBLE);
                    hintText.setText(e.getMessage());
                } finally {
                    hintText.setText("没有相关数据，请刷新");
                }
                break;
            case R.id.btn_hide:
                viewStub.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_change:
                if (hintText != null) {
                    hintText.setText("网络异常，无法刷新，请检查网络");
                }
                break;
        }
    }
}