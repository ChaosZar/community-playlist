package org.chaos.cp.repository;

import org.chaos.cp.entity.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
    Artist findArtistByName(String name);
}
