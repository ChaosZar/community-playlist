package org.chaos.cp.repository;

import org.chaos.cp.entity.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {

    Song save(Song song);

}
