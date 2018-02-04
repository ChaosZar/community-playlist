package org.chaos.cp.rest.json;

import org.chaos.cp.entity.Song;

import java.util.ArrayList;
import java.util.List;

public class SongList {
    private List<Song> songs = new ArrayList<>();

    public SongList(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
