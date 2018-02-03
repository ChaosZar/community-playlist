package org.chaos.cp.connector;

import org.assertj.core.api.Assertions;
import org.chaos.cp.entity.Song;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpotifyMusicStorageTest {
    @Autowired
    private MusicStorage spotifyMusicStorage;

    @Ignore("requires Spotify authorization")
    @Test
    public void testFindSong() throws IOException {
        Collection<Song> songs = spotifyMusicStorage.findSongsByName("Pretender");
        Assertions.assertThat(songs).extracting(Song::getName).isEqualTo("The Pretender");
    }
}
