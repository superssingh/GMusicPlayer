package com.santoshkumarsingh.gmusicplayer.Models;

/**
 * Created by santoshsingh on 17/08/17.
 */

public class AudioData {
    private String TITLE, ARTIST, URL;

    public AudioData() {
    }

    public AudioData(String TITLE, String ARTIST, String URL) {
        this.TITLE = TITLE;
        this.ARTIST = ARTIST;
        this.URL = URL;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getARTIST() {
        return ARTIST;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

}
