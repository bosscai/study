package com.example.test.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.test.R;

public class MVVMActivity extends AppCompatActivity {

    public static final String TAG = "MVVMActivity";

    private TestViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvmactivity);

        model = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TestViewModel.class);
    }
}