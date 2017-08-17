package com.santoshkumarsingh.gmusicplayer.Activities;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.santoshkumarsingh.gmusicplayer.Fragments.BaseFragment;
import com.santoshkumarsingh.gmusicplayer.R;

import java.util.jar.Manifest;


public class MainActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener{

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mediaPlayer = MediaPlayer.create(this, R.raw.shapeofyou_karaoke);
//        mediaPlayer.start(); // no need to call prepare(); create() does that for you

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mediaPlayer.release();
//        mediaPlayer = null;
    }

    @Override
    public void onFragmentInteraction(String data) {

    }


    public class MyService extends Service implements MediaPlayer.OnPreparedListener {
        private static final String ACTION_PLAY = "com.example.action.PLAY";
        MediaPlayer mMediaPlayer = null;


        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {

        }
    }


}
