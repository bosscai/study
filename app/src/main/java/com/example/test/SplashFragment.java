package com.example.test;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/10/29
 * describe: 入场动画的的Fragment
 **/
public class SplashFragment extends Fragment {

    private ImageView mImageView;
    private Button mButton;

    private AnimationDrawable mAnimationDrawable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_layout_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImageView = view.findViewById(R.id.img_splash);
        mButton = view.findViewById(R.id.btn_start_splash);

        mAnimationDrawable =new AnimationDrawable();
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car01),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car02),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car03),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car04),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car05),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car06),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car07),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car08),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car09),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car10),50);

        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car11),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car12),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car13),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car14),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car15),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car16),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car17),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car18),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car19),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car20),50);

        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car21),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car22),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car23),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car24),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car25),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car26),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car27),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car28),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car29),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car30),50);

        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car31),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car32),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car33),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car34),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car35),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car36),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car37),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car38),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car39),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car40),50);

        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car41),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car42),50);
        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.car43),50);
        mAnimationDrawable.setOneShot(true);//设置循环播放
        mAnimationDrawable.setOneShot(true);//设置循环播放
        mImageView.setBackground(mAnimationDrawable);

        mButton.setOnClickListener(v -> {
            mAnimationDrawable.start();
        });
    }
}
