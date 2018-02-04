package org.chaos.cp.rest.json;


import org.chaos.cp.entity.Song;

import java.util.List;

public class SetPlaylistRequest {

    private Long userId;

    private List<Song> songs;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
