package org.chaos.cp.rest.json;


import java.util.Collection;

public class SetPlaylistRequest {

    private Long userId;

    private Collection<Long> songIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Collection<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(Collection<Long> songIds) {
        this.songIds = songIds;
    }
}
