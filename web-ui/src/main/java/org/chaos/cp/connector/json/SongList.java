package org.chaos.cp.connector.json;

import java.util.ArrayList;
import java.util.List;

public class SongList {
    private List<Song> songs = new ArrayList<>();

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
