package org.chaos.cp.connector;

import com.wrapper.spotify.exceptions.WebApiException;
import org.chaos.cp.entity.Song;

import java.io.IOException;
import java.util.Collection;

public interface MusicStorage {
    Collection<Song> findSongsByName(String name) throws IOException;
}
