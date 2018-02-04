package org.chaos.cp.connector.json;

import java.util.ArrayList;
import java.util.List;

public class PutPlaylistRequest {
    private Integer userId = 0;
    private List<Song> songs = new ArrayList<>();

    public PutPlaylistRequest(Integer id, List<Song> userPlaylist) {
        userId = id;
        songs = userPlaylist;
    }

    public PutPlaylistRequest() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
