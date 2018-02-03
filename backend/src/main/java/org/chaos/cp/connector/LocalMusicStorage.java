package org.chaos.cp.connector;

import org.chaos.cp.entity.Song;
import org.chaos.cp.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LocalMusicStorage implements MusicStorage {
    @Autowired
    private SongRepository songRepository;

    @Override
    public Collection<Song> findSongsByTitle(String title) {
        return songRepository.findSongsByTitleContaining(title);
    }
}
