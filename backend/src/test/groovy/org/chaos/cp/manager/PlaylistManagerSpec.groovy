package org.chaos.cp.manager

import org.chaos.cp.entity.Playlist
import org.chaos.cp.entity.UserSong
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class PlaylistManagerSpec extends Specification {

    def 'should return the highest rating of a Song from a list of Playlists'() {
        given: 'a PlaylistManager'
        PlaylistManager playlistManager = new PlaylistManager()

        and: 'a List of playlists'
        Playlist playlist1 = new Playlist(
                songs: [new UserSong(rank: 1), new UserSong(rank: 7), new UserSong(rank: 9)]
        )

        Playlist playlist2 = new Playlist(
                songs: [new UserSong(rank: 5), new UserSong(rank: 100), new UserSong(rank: 5)]
        )
        List<Playlist> playlists = [playlist1, playlist2]

        expect: 'getHighestSongRating is called, the highest ranking is returned'
        playlistManager.getHighestSongRating(playlists) == 100
    }

}
