package com.example.itubeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itubeapp.model.Video;

import java.util.ArrayList;

public class RecyclerPlaylistAdapter extends RecyclerView.Adapter<RecyclerPlaylistAdapter.MyViewHolder> {

    Context context;
    ArrayList<Video> videos;
    public RecyclerPlaylistAdapter(Context context, ArrayList<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @NonNull
    @Override
    public RecyclerPlaylistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_row,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerPlaylistAdapter.MyViewHolder holder, int position) {
        holder.videoURL.setText(videos.get(position).getUrl());
        holder.cardRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlayVideoActivity.class);
                intent.putExtra("url", videos.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView videoURL;
        LinearLayout cardRoot;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            videoURL = itemView.findViewById(R.id.video_url);
            cardRoot = itemView.findViewById(R.id.card_root);
        }
    }
}
