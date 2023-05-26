package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itubeapp.data.PlaylistDatabaseHelper;
import com.example.itubeapp.model.Video;

import java.util.UUID;

public class HomeActivity extends AppCompatActivity {
    EditText ytVidURL;
    Button play, addToPlayListButton,myPlayListButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ytVidURL = findViewById(R.id.yt_vid_url);
        play = findViewById(R.id.play);
        addToPlayListButton = findViewById(R.id.add_to_playlist);
        myPlayListButton = findViewById(R.id.my_playlist);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playButtonHandle();
            }
        });
        addToPlayListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToPlaylist();
            }
        });
        myPlayListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MyPlaylistActivity.class);
                startActivity(intent);
            }
        });
    }

    private void playButtonHandle() {
        String url = ytVidURL.getText().toString();
        if (url.matches("")) {
            showToast("You did not enter any URL");
            return;
        }
        Intent intent = new Intent(HomeActivity.this, PlayVideoActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    private void addToPlaylist() {
        String url = ytVidURL.getText().toString();
        if (url.matches("")) {
            showToast("You did not enter any URL");
            return;
        }
        Video video = new Video(UUID.randomUUID().toString(), url);
        PlaylistDatabaseHelper db = new PlaylistDatabaseHelper(HomeActivity.this);
        Boolean check = db.addVideo(video);
        if (check) {
            showToast("Added to playlist");
            ytVidURL.setText("");
        } else {
            showToast("Add to playlist failed");
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
