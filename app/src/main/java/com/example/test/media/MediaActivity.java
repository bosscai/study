package com.example.test.media;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media.MediaBrowserServiceCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.support.v4.media.*;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class MediaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MediaBrowserCompat browserCompat;

    private MediaBrowserServiceCompat mediaBrowserServiceCompat;

    private MediaSession mediaSession;

    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initView();

//        browserCompat = new MediaBrowserCompat(this,
//                new ComponentName(),
//                new MediaBrowserCompat.ConnectionCallback() {},
//                null);

    }

    private void initView() {
        List<String> mediaItems = new ArrayList<>();
        mediaItems.add("Song 1");
        mediaItems.add("Song 2");
        mediaItems.add("Song 3");
        mediaItems.add("Song 4");
        MediaAdapter mediaAdapter = new MediaAdapter();
        mediaAdapter.setList(mediaItems);
        recyclerView = findViewById(R.id.media_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mediaAdapter);
    }
}