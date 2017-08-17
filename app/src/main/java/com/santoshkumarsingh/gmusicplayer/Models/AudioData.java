package com.santoshkumarsingh.gmusicplayer.Models;

/**
 * Created by santoshsingh on 17/08/17.
 */

public class AudioData {
    private String TITLE, ARTIST, URL, THUMBNAIL;

    public AudioData(String TITLE, String ARTIST, String URL, String THUMBNAIL) {
        this.TITLE = TITLE;
        this.ARTIST = ARTIST;
        this.URL = URL;
        this.THUMBNAIL = THUMBNAIL;
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

    public String getTHUMBNAIL() {
        return THUMBNAIL;
    }

    public void setTHUMBNAIL(String THUMBNAIL) {
        this.THUMBNAIL = THUMBNAIL;
    }
}
