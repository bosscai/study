package com.example.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder> {

    private List<String> data;
    private onClickListener listener;

    public MainRecyclerViewAdapter() { }

    public MainRecyclerViewAdapter(List<String> data) {
        this.data = data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public void setListener(onClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_main, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position);
            }
        });
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
    public interface onClickListener{
        void onClick(int pos);
    }
}
