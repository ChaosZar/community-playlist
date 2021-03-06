package org.chaos.cp.entity;

import javax.persistence.*;

@Entity
public class UserSong implements Comparable<UserSong> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "songId")
    private Song song;


    private Integer rank = 0;

    private Integer position = 0;

    public UserSong() {

    }

    public UserSong(Song song, Integer position) {
        this.song = song;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int compareTo(UserSong o) {
        return this.getPosition().compareTo(o.getPosition());
    }

    public Integer getPosition() {
        return position;
    }

    public Song getSong() {
        return song;
    }

    public Integer getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "UserSong{" +
                "id=" + id +
                ", song=" + song +
                ", rank=" + rank +
                ", position=" + position +
                '}';
    }
}
