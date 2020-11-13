package com.example.romanobolonskiy;

public class MusicTrack {

    private String song;
    private String executor;
    private String album;

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public MusicTrack(String song, String executor, String album, String time) {
        this.song = song;
        this.executor = executor;
        this.album = album;
        this.time = time;
    }
}
