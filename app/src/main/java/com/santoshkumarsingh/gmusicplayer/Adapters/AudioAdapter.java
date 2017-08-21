package com.santoshkumarsingh.gmusicplayer.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.santoshkumarsingh.gmusicplayer.Models.AudioData;
import com.santoshkumarsingh.gmusicplayer.R;

import java.util.List;

/**
 * Created by santoshsingh on 17/08/17.
 */

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder>{

    Context context;
    private List<AudioData> audioDataList;
    SongOnClickListener SongOnClickListener;

    public AudioAdapter(Context context ) {
        this.context=context;
    }

    @Override
    public AudioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AudioAdapter.ViewHolder holder, final int position) {
        final AudioData audioData = audioDataList.get(position);
        holder.mTitle.setText(audioData.getTITLE());
        holder.mArtist.setText(audioData.getARTIST());

        holder.mPlay_Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SongOnClickListener!=null){
                    SongOnClickListener.OnClick(holder.mPlay_Pause,holder.itemView,audioData.getURL(),position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return audioDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTitle, mArtist;
        private ImageButton mPlay_Pause;
        public ViewHolder(View itemView) {
            super(itemView);
            mTitle=(TextView)itemView.findViewById(R.id.song_Title);
            mArtist=(TextView)itemView.findViewById(R.id.song_Artist);
            mPlay_Pause=(ImageButton) itemView.findViewById(R.id.play_Pause);
        }
    }

    public void addSongs(List<AudioData> audioDataList){
        this.audioDataList = audioDataList;
        notifyDataSetChanged();
    }

    public interface SongOnClickListener {
        void OnClick(ImageButton button, View view, String URL, int position);
    }

    public void setOnClickListener(SongOnClickListener SongOnClickListener){
        this.SongOnClickListener=SongOnClickListener;
    }
}
