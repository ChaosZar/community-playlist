package org.chaos.cp.connector.json;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private Integer id = 0;
    private List<UserSong> songs = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<UserSong> getSongs() {
        return songs;
    }

    public void setSongs(List<UserSong> songs) {
        this.songs = songs;
    }
}
