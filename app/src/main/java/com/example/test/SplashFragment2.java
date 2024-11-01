package com.example.test;

import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/10/29
 * describe: 入场动画的的Fragment
 **/
public class SplashFragment2 extends Fragment {

    private VideoView mVideoView;
    private Button mButton;

    private AnimationDrawable mAnimationDrawable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_layout_splash2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mButton = view.findViewById(R.id.btn_start_splash);
        mVideoView = view.findViewById(R.id.video_view);

        mVideoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.car));

        mButton.setOnClickListener(v -> {
            mVideoView.start();
        });
    }
}
