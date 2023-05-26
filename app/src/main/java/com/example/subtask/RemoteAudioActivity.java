package com.example.subtask;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class RemoteAudioActivity extends AppCompatActivity {
    private Button playButton;
    private MediaPlayer mediaPlayer;
    private String url = "https://www.kozco.com/tech/piano2-CoolEdit.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_audio);
        initializeViews();
        setupMediaPlayer();
        setupPlayButton();
    }

    private void initializeViews() {
        playButton = findViewById(R.id.playButton);
    }

    private void setupMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (IOException e) {
            System.out.println("prepare() failed");
        }
    }

    private void setupPlayButton() {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("playButton Pressed");
                startMediaPlayer();
            }
        });
    }

    private void startMediaPlayer() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }
}
