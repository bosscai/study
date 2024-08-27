package com.example.test.model;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/8/28
 * describe:
 **/
public class MainItem {
    private String title;
    private ItemClickListener listener;

    public MainItem(String title, ItemClickListener listener) {
        this.title = title;
        this.listener = listener;
    }

    public String getTitle() {
        return title;
    }

    public ItemClickListener getListener() {
        return listener;
    }

    public interface ItemClickListener {
        void onItemClick(int position, String title);
    }
}
