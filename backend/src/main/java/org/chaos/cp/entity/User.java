package org.chaos.cp.entity;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    @OneToOne
    private Playlist playlist;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, login='%s']",
                id, login);
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
