package org.chaos.cp.entity;

import javax.persistence.*;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

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

    // filename, spotify uri, ... XXX change type to URI?
    private String ref;

    public Song() {
    }

    public Song(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", ref=" + ref +
                '}';
    }
}
