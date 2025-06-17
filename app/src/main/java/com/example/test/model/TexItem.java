package com.example.test.model;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/8/28
 * describe:
 **/
public class TexItem {
    private String title;
    private int resID;

    public TexItem(String title, int resID) {
        this.title = title;
        this.resID = resID;
    }

    public String getTitle() {
        return title;
    }

    public int getResID() {
        return resID;
    }
}
