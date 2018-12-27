package com.example.gerffyxuuu.assignment1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.LinkedList;
import java.util.List;

public class VideosActivity extends AppCompatActivity {
    private List<Video> mData = null;
    private Context mContext;
    private VideoAdapter mAdapter = null;
    private ListView list_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos);

        mContext = VideosActivity.this;
        list_video = (ListView) findViewById(R.id.list_videos);
        mData = new LinkedList<Video>();


        mData.add(new Video(R.mipmap.articles1, "腹肌撕裂者", "腹肌撕裂者","观看人数：100"));
        mData.add(new Video(R.mipmap.articles1, "震惊！健身中居然发送这样的事。", "腹肌撕裂者！","观看人数：10"));
        mAdapter = new VideoAdapter((LinkedList<Video>) mData, mContext);
        list_video.setAdapter(mAdapter);
    }
}
