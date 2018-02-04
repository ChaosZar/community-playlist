package org.chaos.cp.connector;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.ArtistSearchRequest;
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
    // Create an API instance. The default instance connects to https://api.spotify.com/.
    private static final Api API = Api.DEFAULT_API;

    @Override
    public Collection<Song> findSongsByTitle(String title) throws IOException {
        TrackSearchRequest searchRequest = API.searchTracks(title).build();

        try {
            Page<Track> trackPage = searchRequest.get();
            return trackPage.getItems().stream()
                    .map(track -> new Song(track.getName())).collect(Collectors.toList());
        } catch (WebApiException e) {
            throw new IOException(e);
        }
    }

    @Override
    public Collection<Song> findSongsByArtist(String artistName) throws IOException {
        ArtistSearchRequest searchRequest = API.searchArtists(artistName).build();

        try {
            Page<com.wrapper.spotify.models.Artist> trackPage = searchRequest.get();
            throw new UnsupportedOperationException("not implemented yet");
        } catch (WebApiException e) {
            throw new IOException(e);
        }
    }

    @Override
    public Song getSong(Long id) throws IOException {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
