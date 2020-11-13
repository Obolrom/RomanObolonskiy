package com.example.romanobolonskiy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicTrackAdapter extends RecyclerView.Adapter<MusicTrackAdapter.MusicTrackHolder> {

    public interface ActivityConnector {
        void recyclerItemSelected(int index);
    }

    private ActivityConnector context;
    private ArrayList<MusicTrack> musicTracks;

    public MusicTrackAdapter(Context context, ArrayList<MusicTrack> list) {
        this.context = (ActivityConnector) context;
        this.musicTracks = list;
    }

    public class MusicTrackHolder extends RecyclerView.ViewHolder {

        protected ImageView note;
        protected TextView song;
        protected TextView executor;
        protected TextView album;
        protected TextView time;

        public MusicTrackHolder(@NonNull View itemView) {
            super(itemView);

            note = itemView.findViewById(R.id.note_iv);
            song = itemView.findViewById(R.id.tv_song_name);
            executor = itemView.findViewById(R.id.tv_executor);
            album = itemView.findViewById(R.id.tv_album);
            time = itemView.findViewById(R.id.tv_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.recyclerItemSelected(musicTracks.indexOf((MusicTrack) view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public MusicTrackAdapter.MusicTrackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.music_track, parent, false);

        return new MusicTrackHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicTrackAdapter.MusicTrackHolder holder, int position) {
        holder.itemView.setTag(musicTracks.get(position));

        holder.song.setText(musicTracks.get(position).getSong());
        holder.executor.setText(musicTracks.get(position).getExecutor());
        holder.album.setText(musicTracks.get(position).getAlbum());
        holder.time.setText(musicTracks.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return musicTracks.size();
    }
}
