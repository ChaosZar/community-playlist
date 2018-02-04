package cp.chaos.org.communityplaylist.json;

import java.util.List;

public class PlaylistPut {
    private List<Song> songs;
    private long userId;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
