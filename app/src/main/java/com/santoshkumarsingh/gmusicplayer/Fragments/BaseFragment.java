package com.santoshkumarsingh.gmusicplayer.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.santoshkumarsingh.gmusicplayer.Adapters.SongAdapter;
import com.santoshkumarsingh.gmusicplayer.Models.Song;
import com.santoshkumarsingh.gmusicplayer.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private List<Song> songList;
    SongAdapter songAdapter;
    private View view;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RecyclerView.LayoutManager linearLayoutManager;

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
        songList = new ArrayList<>();
        checkPermission();
        configRecycleView();

        return view;
    }

    private void configRecycleView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songAdapter);
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 24);
                return;
            }
        } else {
            loadSongs();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 24:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadSongs();
                } else {
                    Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_LONG).show();
                    checkPermission();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void loadSongs() {

        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                    Song song = new Song(title, artist, url);
                    songList.add(song);
                } while (cursor.moveToNext());
            }
            cursor.close();
            songAdapter = new SongAdapter(mListener,songList);
            songAdapter.notifyDataSetChanged();

        }else{
            Toast.makeText(getActivity(),"Query Failed, Please Retry",Toast.LENGTH_SHORT).show();
        }

    }

//    private void setData() {
//        for (int i = 0; i < 11; i++) {
//            Song song = new Song();
//            song.setTITLE("Song Title-" + i);
//            song.setARTIST("Song Artist Name" + i);
//            song.setURL("Song URL");
//
//            songList.add(song);
//        }
//        songAdapter.addSongs(songList);
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        songList.clear();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String data);
    }
}
