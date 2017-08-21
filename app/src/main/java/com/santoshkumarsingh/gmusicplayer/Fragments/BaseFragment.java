package com.santoshkumarsingh.gmusicplayer.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.santoshkumarsingh.gmusicplayer.Adapters.AudioAdapter;
import com.santoshkumarsingh.gmusicplayer.Models.AudioData;
import com.santoshkumarsingh.gmusicplayer.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.santoshkumarsingh.gmusicplayer.R.drawable.ic_pause;

public class BaseFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

//    private OnFragmentInteractionListener mListener;
    private List<AudioData> audioDataList;
    private View view;
    private LinearLayoutManager linearLayoutManager;
    AudioAdapter audioAdapter;
    MediaPlayer mediaPlayer;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.bind(this, view);
        audioDataList = new ArrayList<>();
        mediaPlayer = new MediaPlayer();
        configRecycleView();
        checkPermission();

        return view;
    }

    private void setSongIntoMediaPlayer(String songUrl) {

    }

    private void configRecycleView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(itemDecoration);
        audioAdapter = new AudioAdapter(getActivity());
        audioAdapter.addSongs(audioDataList);
        audioAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(audioAdapter);

        audioAdapter.setOnClickListener(new AudioAdapter.SongOnClickListener() {
            @Override
            public void OnClick(final ImageButton button, View view, String URL, int position) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    button.setBackgroundResource(R.drawable.ic_play);
                }else if(mediaPlayer.equals(position)){
                    mediaPlayer.start();
                }else {
                    try {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(URL);
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                                button.setBackgroundResource(ic_pause);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 24);
                return;
            }
        }
        loadAudioFiles();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 24:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadAudioFiles();
                } else {
                    Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_LONG).show();
                    checkPermission();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void loadAudioFiles() {
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                    AudioData audioData = new AudioData(title, artist, url);
                    audioDataList.add(audioData);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
        mediaPlayer.release();
        mediaPlayer=null;
        audioDataList.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }




//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Button button, View view, String songURL, int Position);
//    }
}
