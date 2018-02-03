package org.chaos.cp.repository;

import org.chaos.cp.entity.Playlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {

    List<Playlist> findAll();

    Playlist save(Playlist playlist);
}
