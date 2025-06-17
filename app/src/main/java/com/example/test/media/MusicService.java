package com.example.test.media;

import static android.media.session.MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.MediaBrowserServiceCompat;

import java.util.List;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/9/24
 * describe:
 **/
public class MusicService extends MediaBrowserServiceCompat {

    public final static String TAG = "MusicService";
    private MediaSessionCompat mSession;
    private PlaybackStateCompat mPlaybackState;

    private MediaPlayer mMediaPlayer;

    private MediaSessionCompat.Callback callback = new MediaSessionCompat.Callback() {

    };

    private MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            Log.i(TAG, "onPrepared: ");
            mMediaPlayer.start();
            mPlaybackState = new PlaybackStateCompat.Builder()
                    .setState(PlaybackStateCompat.STATE_PLAYING, 0, 1.0f)
                    .build();
            mSession.setPlaybackState(mPlaybackState);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mPlaybackState = new PlaybackStateCompat.Builder()
                .setState(PlaybackStateCompat.STATE_NONE, 0, 1.0f)
                .build();
        mSession = new MediaSessionCompat(this, TAG);
        mSession.setCallback(callback);
        mSession.setFlags(MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mSession.setPlaybackState(mPlaybackState);
        //设置token后会触发MediaBrowserCompat.ConnectionCallback的回调方法
        //表示MediaBrowser与MediaBrowserService连接成功
        setSessionToken(mSession.getSessionToken());

//        mMediaPlayer = new MediaPlayer();
//        mMediaPlayer.setOnPreparedListener();
//        mMediaPlayer.setOnCompletionListener();

    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        return null;
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {

    }
}
