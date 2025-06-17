package com.example.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.TexItem;

import java.util.ArrayList;

public class CMBViewAdapter extends RecyclerView.Adapter<CMBViewAdapter.CMBViewHolder> {

    private ArrayList<TexItem> data;

    public CMBViewAdapter() { }

    public CMBViewAdapter(ArrayList<TexItem> data) {
        this.data = data;
    }

    public void setData(ArrayList<TexItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CMBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_cmb, parent, false);
        return new CMBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CMBViewHolder holder, int position) {
        String title = data.get(position).getTitle();
//        holder.textView.setText(title);
//        holder.imageView.setImageResource(data.get(position).getResID());

    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        }
        return -1;
    }

    class CMBViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public CMBViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_content);
            imageView = itemView.findViewById(R.id.img_show);
        }
    }
}
