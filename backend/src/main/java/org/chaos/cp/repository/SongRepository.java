package org.chaos.cp.repository;

import org.chaos.cp.entity.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface SongRepository extends CrudRepository<Song, Long> {

    Collection<Song> findSongsByTitleContainingIgnoreCase(String title);
    Collection<Song> findSongsByArtist_NameContainingIgnoreCase(String artistName);
}
