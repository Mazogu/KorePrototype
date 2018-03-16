package com.example.micha.remotekodi.media;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.micha.remotekodi.BaseActivity;
import com.example.micha.remotekodi.R;

public class MediaActivity extends BaseActivity {
    public static final String MUSIC = "music_mode";
    public static final String MOVIE = "movie_mode";
    public static final String TV = "tv_mode";
    public static final String FAVORITES = "favorites_mode";
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup views = findViewById(R.id.content);
        getLayoutInflater().inflate(R.layout.activity_media, views);
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.mediaList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        if(intent != null){
            String action = intent.getAction();
        }
    }
}
