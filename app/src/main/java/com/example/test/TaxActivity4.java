package com.example.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.adapter.MainRecyclerViewAdapter;
import com.example.test.adapter.TexViewAdapter;
import com.example.test.model.TexItem;

import java.util.ArrayList;

public class TaxActivity4 extends AppCompatActivity {

    private RecyclerView recyclerview;

    private ArrayList<TexItem> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_tax);
        data.add(new TexItem("2025-05", R.drawable.tax_item));
        data.add(new TexItem("2025-04", R.drawable.tax_item));
        data.add(new TexItem("2025-03", R.drawable.tax_item));
        data.add(new TexItem("2025-02", R.drawable.tax_item_2));
        data.add(new TexItem("2025-01", R.drawable.tax_item_3));
        data.add(new TexItem("2025-01", R.drawable.tax_item_1));
        data.add(new TexItem("2025-01", R.drawable.tax_item));
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(new TexViewAdapter(data));
        findViewById(R.id.btn_exit).setOnClickListener(view -> {
            finish();
        });
    }
}