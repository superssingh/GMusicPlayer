package com.santoshkumarsingh.gmusicplayer.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.santoshkumarsingh.gmusicplayer.Fragments.BaseFragment;
import com.santoshkumarsingh.gmusicplayer.Models.Song;
import com.santoshkumarsingh.gmusicplayer.R;

import java.util.List;

/**
 * Created by santoshsingh on 17/08/17.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{

    private List<Song> songList;
    private BaseFragment.OnFragmentInteractionListener listener;

    public SongAdapter(BaseFragment.OnFragmentInteractionListener listener, List<Song> songList) {
        this.listener=listener;
        this.songList=songList;
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SongAdapter.ViewHolder holder, int position) {
        final Song song = songList.get(position);
        holder.mTitle.setText(song.getTITLE());
        holder.mArtist.setText(song.getARTIST());
        holder.mPlay_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFragmentInteraction(song.getURL());
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitle, mArtist;
        private Button mPlay_Pause;
        public ViewHolder(View itemView) {
            super(itemView);
            mTitle=(TextView)itemView.findViewById(R.id.song_Title);
            mArtist=(TextView)itemView.findViewById(R.id.song_Artist);
            mPlay_Pause=(Button)itemView.findViewById(R.id.play_Pause);
        }


    }

    public void addSongs(List<Song> songList){
        this.songList = songList;
        notifyDataSetChanged();
    }
}
