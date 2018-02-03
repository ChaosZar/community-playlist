package org.chaos.cp.repository;

import org.chaos.cp.entity.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
}
