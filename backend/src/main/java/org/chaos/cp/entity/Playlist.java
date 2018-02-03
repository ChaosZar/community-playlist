package org.chaos.cp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "artistId")
    private List<Song> songs = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void add(Song song) {
        songs.add(song);
    }

    public Song get(int index) {
        return songs.get(index);
    }
}
