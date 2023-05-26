package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.itubeapp.data.PlaylistDatabaseHelper;

public class MyPlaylistActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_playlist);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlaylistDatabaseHelper db = new PlaylistDatabaseHelper(this);
        RecyclerPlaylistAdapter adapter = new RecyclerPlaylistAdapter(this, db.getPlaylist());
        recyclerView.setAdapter(adapter);
    }
}