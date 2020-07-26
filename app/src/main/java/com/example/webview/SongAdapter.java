package com.example.webview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private Context context;
    private Song[] data;

    public SongAdapter(Context context, Song[] data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.song_list_layout,parent,false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        final Song song=data[position];
        holder.songName.setText(song.getSong());
        holder.songArtist.setText(song.getArtists());
        Glide.with(holder.songImage.getContext()).load(song.getCoverImage()).into(holder.songImage);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    public class SongViewHolder extends RecyclerView.ViewHolder{

        ImageView songImage;
        TextView songName,songArtist;

        public SongViewHolder(View itemView) {
            super(itemView);

            songImage=itemView.findViewById(R.id.songImage);
            songName=itemView.findViewById(R.id.songName);
            songArtist=itemView.findViewById(R.id.songArtist);

        }
    }
}
