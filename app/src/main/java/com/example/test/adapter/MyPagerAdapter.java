package com.example.test.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.test.mvvm.fragment.BaseFragment;


/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/8/19
 * describe:
 **/
public class MyPagerAdapter extends FragmentPagerAdapter {

    private BaseFragment[] fragments;
    

    public MyPagerAdapter(@NonNull FragmentManager fm, BaseFragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public MyPagerAdapter(@NonNull FragmentManager fm, int behavior, BaseFragment[] fragments) {
        super(fm, behavior);
        this.fragments = fragments;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
