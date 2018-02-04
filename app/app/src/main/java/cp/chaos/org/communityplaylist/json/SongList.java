package cp.chaos.org.communityplaylist.json;

import java.util.List;

/**
 * @author jbuecker
 * @since 4.6.
 */

public class SongList {
    private List<Song> songs;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
