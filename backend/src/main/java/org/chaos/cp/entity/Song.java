package org.chaos.cp.entity;

import javax.persistence.*;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinTable(
            name = "song_playlist",
            joinColumns =
            @JoinColumn(
                    name = "song_id",
                    referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(
                    name = "publisher_id",
                    referencedColumnName = "id")
    )
    private Artist artist;

    public Song(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
