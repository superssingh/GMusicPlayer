package com.santoshkumarsingh.gmusicplayer.Models;

/**
 * Created by santoshsingh on 17/08/17.
 */

public class Song {
    private String TITLE, ARTIST, URL;

    public Song(String TITLE, String ARTIST, String URL) {
        this.TITLE = TITLE;
        this.ARTIST = ARTIST;
        this.URL = URL;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getARTIST() {
        return ARTIST;
    }

    public void setARTIST(String ARTIST) {
        this.ARTIST = ARTIST;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
