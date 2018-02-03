package org.chaos.cp.connector;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;
import org.chaos.cp.entity.Song;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SpotifyMusicStorage implements MusicStorage {

    @Override
    public Collection<Song> findSongsByTitle(String title) throws IOException {
        // Create an API instance. The default instance connects to https://api.spotify.com/.
        Api api = Api.DEFAULT_API;

        TrackSearchRequest searchRequest = api.searchTracks(title).build();

        try {
            Page<Track> trackPage = searchRequest.get();
            return trackPage.getItems().stream()
                    .map(track -> new Song(track.getName())).collect(Collectors.toList());
        } catch (WebApiException e) {
            throw new IOException(e);
        }
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
