package com.example.test.media;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.List;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/9/23
 * describe:
 **/
public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {

    static final String TAG = "MediaAdapter";

    private List<String> list;

    private onItemClickListener clickListener;


    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_media, parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
        holder.textView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onMediaItemClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setClickListener(onItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class MediaViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MediaViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_song_name);
        }
    }

    public interface onItemClickListener {
        void onMediaItemClick();
    }
}
