package org.chaos.cp.connector;

import org.chaos.cp.connector.json.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ServerConnector {

    private static final String BASE_URL = "http://127.0.0.1:8080/";
    private static final String PLAYLIST_ENDPOINT = "playlist/";
    private static final String USER_ENDPOINT = "user/";
    private static final String SONG_ENDPOINT = "song/";
    private static RestTemplate restTemplate = new RestTemplate();


    public User getUser(String user) {
        return getObject(User.class, USER_ENDPOINT + user);
    }

    public List<UserSong> getMasterPlaylist() {
        return getObject(UserSongList.class, PLAYLIST_ENDPOINT + "overall/").getSongs();
    }

    private <E> E getObject(Class<E> clazz, String endpoint) {
        return restTemplate.getForObject(BASE_URL + endpoint, clazz);
    }

    public List<Song> search(String searchText) {
        return getObject(SongList.class, SONG_ENDPOINT + searchText).getSongs();
    }
}
