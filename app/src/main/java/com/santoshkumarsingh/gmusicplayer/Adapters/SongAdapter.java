package com.santoshkumarsingh.gmusicplayer.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.santoshkumarsingh.gmusicplayer.Fragments.BaseFragment;
import com.santoshkumarsingh.gmusicplayer.Models.AudioData;
import com.santoshkumarsingh.gmusicplayer.R;

import java.util.List;

/**
 * Created by santoshsingh on 17/08/17.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{

    private List<AudioData> audioDataList;
    private BaseFragment.OnFragmentInteractionListener listener;

    public SongAdapter(BaseFragment.OnFragmentInteractionListener listener) {
        this.listener=listener;
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SongAdapter.ViewHolder holder, int position) {
        final AudioData audioData = audioDataList.get(position);
        holder.mTitle.setText(audioData.getTITLE());
        holder.mArtist.setText(audioData.getARTIST());
        holder.mPlay_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFragmentInteraction(audioData.getURL());
            }
        });
    }

    @Override
    public int getItemCount() {
        return audioDataList.size();
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

    public void addSongs(List<AudioData> audioDataList){
        this.audioDataList = audioDataList;
        notifyDataSetChanged();
    }
}
