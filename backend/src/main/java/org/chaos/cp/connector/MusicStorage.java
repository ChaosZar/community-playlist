package org.chaos.cp.connector;

import org.chaos.cp.entity.Song;

import java.io.IOException;
import java.util.Collection;

public interface MusicStorage {
    Collection<Song> findSongsByTitle(String title) throws IOException;

    boolean isEnabled();
}
