package org.chaos.cp.rest.json;

import org.chaos.cp.entity.UserSong;

import java.util.ArrayList;
import java.util.List;

public class MasterPlaylistResponse {
    List<UserSong> songs = new ArrayList<>();

    public MasterPlaylistResponse(List<UserSong> songs) {
        this.songs = songs;
    }

    public MasterPlaylistResponse() {
    }

    public List<UserSong> getSongs() {
        return songs;
    }

    public void setSongs(List<UserSong> songs) {
        this.songs = songs;
    }
}
