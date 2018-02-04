package org.chaos.cp.entity;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "userSongId")
    private List<UserSong> songs = new ArrayList<>();

    @PostConstruct
    private void postConstruct() {
        Collections.sort(songs);
    }

    public Long getId() {
        return id;
    }

    public void add(UserSong song) {
        songs.add(song);
        Collections.sort(songs);
    }

    public UserSong get(int index) {
        return songs.get(index);
    }

    public Long size() {
        return Long.valueOf(songs.size());
    }

    public void reset() {
        songs = new ArrayList<>();
    }

    public List<UserSong> getSongs() {
        return songs;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", songs=" + songs +
                '}';
    }
}
