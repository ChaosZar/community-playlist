package org.chaos.cp.manager

import org.chaos.cp.entity.Playlist
import org.chaos.cp.entity.Song
import org.chaos.cp.entity.UserSong
import org.chaos.cp.repository.PlaylistRepository
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class PlaylistManagerSpec extends Specification {


    public static final int RANKING_100 = 100

    def 'Should return the highest rating of a Song from a list of Playlists'() {
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

    def 'Should create a rather fair Master playlist where the ranking is considered'() {
        given: 'a PlaylistRepository'
        PlaylistRepository playlistRepository = Mock(PlaylistRepository) {
            findAll() >> setupUserPlaylists()
        }
        'a PlaylistManager'
        PlaylistManager playlistManager = new PlaylistManager(
                playlistRepository: playlistRepository
        )

        when: 'getMasterPlaylist is called'
        List<Song> masterPlaylist = playlistManager.getMasterPlaylist()

        then: 'following order is returned'
        masterPlaylist[0].id == 6
        masterPlaylist[1].id == 1
        masterPlaylist[2].id == 3
        masterPlaylist[3].id == 5
        masterPlaylist[4].id == 2
        masterPlaylist[5].id == 4
        masterPlaylist[6].id == 7
    }

    def setupUserPlaylists() {
        UserSong userSong1 = new UserSong(id: 1, rank: 0)
        UserSong userSong2 = new UserSong(id: 2, rank: 0)
        UserSong userSong3 = new UserSong(id: 3, rank: 0)
        UserSong userSong4 = new UserSong(id: 4, rank: 0)
        UserSong userSong5 = new UserSong(id: 5, rank: 0)
        UserSong userSong6 = new UserSong(id: 6, rank: RANKING_100)
        UserSong userSong7 = new UserSong(id: 7, rank: 0)

        Playlist playlist1 = new Playlist()
        playlist1.add(userSong1)
        playlist1.add(userSong2)
        playlist1.add(userSong7)

        Playlist playlist2 = new Playlist()
        playlist2.add(userSong3)
        playlist2.add(userSong4)

        Playlist playlist3 = new Playlist()
        playlist3.add(userSong5)
        playlist3.add(userSong6)

        return [playlist1, playlist2, playlist3]
    }
}
