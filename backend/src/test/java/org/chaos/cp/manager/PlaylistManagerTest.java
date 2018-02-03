package org.chaos.cp.manager;

import org.assertj.core.api.Assertions;
import org.chaos.cp.entity.Playlist;
import org.chaos.cp.entity.Song;
import org.chaos.cp.entity.User;
import org.chaos.cp.repository.PlaylistRepository;
import org.chaos.cp.repository.SongRepository;
import org.chaos.cp.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaylistManagerTest {

    @Autowired
    private SongRepository songs;

    @Autowired
    private PlaylistRepository playlists;

    @Autowired
    private UserRepository users;

    @Autowired
    private PlaylistManager playlistManager;

    @Test
    public void createMasterPlaylistOutOfThreeEvenlyPlaylists() {
        //Create a bunch of Songs to add to test playLists
        Song song1 = new Song("Song1");
        Song song2 = new Song("Song2");
        Song song3 = new Song("Song3");
        Song song4 = new Song("Song4");
        Song song5 = new Song("Song5");
        Song song6 = new Song("Song6");
        songs.save(song1);
        songs.save(song2);
        songs.save(song3);
        songs.save(song4);
        songs.save(song5);
        songs.save(song6);

        Playlist playlist1 = new Playlist();
        playlist1.add(song1);
        playlist1.add(song2);

        Playlist playlist2 = new Playlist();
        playlist2.add(song3);
        playlist2.add(song4);

        Playlist playlist3 = new Playlist();
        playlist3.add(song5);
        playlist3.add(song6);

        playlists.save(playlist1);
        playlists.save(playlist2);
        playlists.save(playlist3);

        User user1 = new User("USER_1");
        user1.setPlaylist(playlist1);
        User user2 = new User("USER_2");
        user2.setPlaylist(playlist2);
        User user3 = new User("USER_3");
        user3.setPlaylist(playlist3);

        users.save(user1);
        users.save(user2);
        users.save(user3);

        List<Song> masterPlaylist = playlistManager.getMasterPlaylist();

        System.out.println(masterPlaylist);

        Assertions.assertThat(masterPlaylist.get(0).getId()).isEqualTo(song1.getId());
        Assertions.assertThat(masterPlaylist.get(1).getId()).isEqualTo(song3.getId());
        Assertions.assertThat(masterPlaylist.get(2).getId()).isEqualTo(song5.getId());
        Assertions.assertThat(masterPlaylist.get(3).getId()).isEqualTo(song2.getId());
        Assertions.assertThat(masterPlaylist.get(4).getId()).isEqualTo(song4.getId());
        Assertions.assertThat(masterPlaylist.get(5).getId()).isEqualTo(song6.getId());
    }
}