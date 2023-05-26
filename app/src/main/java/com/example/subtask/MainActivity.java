package com.example.subtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button localAudioButton;
    Button cameraAppButton;
    Button playAudioURLButton;
    Button playVideoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localAudioButton = findViewById(R.id.localAudioButton);
        cameraAppButton = findViewById(R.id.cameraAppButton);
        playAudioURLButton = findViewById(R.id.playAudioURLButton);
        playVideoButton = findViewById(R.id.playVideoButton);
        localAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("localAudioButton Pressed");
                handleLocalAudio();
            }
        });
        cameraAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("cameraAppButton Pressed");
                handleCameraApp();
            }
        });
        playAudioURLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("playAudioURLButton Pressed");
                handleRemoteAudio();
            }
        });
        playVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("playVideoButton Pressed");
                handleRemoteVideo();
            }
        });
    }

    private void handleLocalAudio() {
        Intent i= new Intent(MainActivity.this,LocalAudioActivity.class);
        startActivity(i);
    }
    private void handleCameraApp() {
        Intent i= new Intent(MainActivity.this,CameraActivity.class);
        startActivity(i);
    }
    private void handleRemoteAudio() {
        Intent i= new Intent(MainActivity.this,RemoteAudioActivity.class);
        startActivity(i);
    }
    private void handleRemoteVideo() {
        Intent i= new Intent(MainActivity.this,RemoteVideoActivity.class);
        startActivity(i);
    }
}