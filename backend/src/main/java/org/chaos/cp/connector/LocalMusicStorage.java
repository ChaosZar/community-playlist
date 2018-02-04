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
        return songRepository.findSongsByTitleContainingIgnoreCase(title);
    }

    @Override
    public Collection<Song> findSongsByArtist(String artistName) {
        return songRepository.findSongsByArtist_NameContainingIgnoreCase(artistName);
    }

    @Override
    public Song getSong(Long id) {
        return songRepository.findOne(id);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
