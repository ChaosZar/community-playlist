package org.chaos.cp.rest

import org.chaos.cp.entity.Song
import org.chaos.cp.entity.User
import org.chaos.cp.repository.SongRepository
import org.chaos.cp.rest.json.SetPlaylistRequest
import org.chaos.cp.rest.json.SongList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class IntegrationSpec extends Specification {

    public static final String USER_NAME = "USER_NAME"
    public static final String SONG_NAME = "SONG_NAME"
    @Autowired
    private PlaylistService playlistService

    @Autowired
    private SongService songService

    @Autowired
    private UserService userService

    @Autowired
    private SongRepository songRepository

    def 'Should login, search a Song, add song to the playlist and view the master playlist'() {
        given: 'a persisted Song'
        Song song = new Song(SONG_NAME)
        song = songRepository.save(song)

        expect: 'song is peristed and has an id'
        song.id != null

        when: 'user is created'
        User user = userService.getUser(USER_NAME)

        then: 'User is persisted and id is not null'
        user.id != null

        when: ''
        SongList songList = songService.findSongsByName(SONG_NAME)

        then: ''
        songList != null
        songList.getSongs().size() == 1

        when: ''
        playlistService.setPlaylistForUser(
                new SetPlaylistRequest(
                        userId: user.id,
                        songs: songList.getSongs()
                )
        )

        then:
        playlistService.getUserPlaylist(user.getId()) != null

        when: ''
        def playlist = playlistService.getCommunityPlaylist()

        then: ''
        !playlist.getSongs().isEmpty()
    }

}
