package org.chaos.cp.connector;

import org.chaos.cp.entity.Song;

import java.io.IOException;
import java.util.Collection;

public interface MusicStorage {
    Collection<Song> findSongsByTitle(String title) throws IOException;
    Collection<Song> findSongsByArtist(String artistName) throws IOException;
    Song getSong(Long id) throws IOException;

    boolean isEnabled();
}
