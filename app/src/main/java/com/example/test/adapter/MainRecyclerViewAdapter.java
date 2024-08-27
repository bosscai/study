package com.example.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.MainItem;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder> {

    private List<MainItem> data;

    public MainRecyclerViewAdapter() { }

    public MainRecyclerViewAdapter(List<MainItem> data) {
        this.data = data;
    }

    public void setData(List<MainItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_main, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        String title = data.get(position).getTitle();
        MainItem.ItemClickListener listener = data.get(position).getListener();
        holder.textView.setText(title);
        holder.textView.setOnClickListener(view -> listener.onItemClick(position, title));
    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        }
        return -1;
    }

    class MainViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_content);
        }
    }
}
