package org.chaos.cp.manager;

import org.apache.commons.lang3.Validate;
import org.chaos.cp.entity.Playlist;
import org.chaos.cp.entity.Song;
import org.chaos.cp.entity.User;
import org.chaos.cp.entity.UserSong;
import org.chaos.cp.repository.PlaylistRepository;
import org.chaos.cp.repository.SongRepository;
import org.chaos.cp.repository.UserRepository;
import org.chaos.cp.repository.UserSongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class PlaylistManager {

    private static final Logger LOG = LoggerFactory.getLogger(PlaylistManager.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserSongRepository userSongRepository;

    @Transactional
    public List<UserSong> getMasterPlaylist() {
        List<UserSong> result = new ArrayList<>();

        List<Playlist> allPlaylists = getAllPlaylistsAsList();
        LOG.debug("Following Playlists found: " + allPlaylists);

        for (Integer maxRanking = getHighestSongRating(allPlaylists); maxRanking >= 0; maxRanking--) {
            addSongsToResult(allPlaylists, maxRanking, result);
        }

        return result;
    }

    private List<Playlist> getAllPlaylistsAsList() {
        List<Playlist> allPlaylists = new ArrayList<>();
        for (Playlist playlist : playlistRepository.findAll()) {
            allPlaylists.add(playlist);
        }
        return allPlaylists;
    }

    /**
     * Populates the given parameter <i>result</i> with the songs of all user in a rather fair way.
     * Only Songs with a certain ranking will be added to the list, other songs will be ignored.
     *
     * @param allPlaylists a list of all user playlists
     * @param targetRank   the ranking a song has to have to be added to the list
     * @param result       a list that may be pre populated with songs. All new found songs will be added to this list.
     */
    private void addSongsToResult(final List<Playlist> allPlaylists, final Integer targetRank, List<UserSong> result) {
        final Long highestPlaylistSize = getHighestPlaylistSize(allPlaylists);
        Integer playlistIndex = 0;
        Integer playlistSongIndex = 0;
        while (playlistSongIndex < highestPlaylistSize) {
            if (playlistIndex == allPlaylists.size()) {
                playlistIndex = 0;
                playlistSongIndex++;
            }
            if (playlistSongIndex < allPlaylists.get(playlistIndex).size()) {
                UserSong potentialSongToAdd = allPlaylists.get(playlistIndex).get(playlistSongIndex);
                if (potentialSongToAdd.getRank().equals(targetRank)) {
                    result.add(potentialSongToAdd);
                }
            }
            playlistIndex++;
        }
    }

    private Long getHighestPlaylistSize(List<Playlist> allPlaylists) {
        return allPlaylists.stream()
                .max(Comparator.comparing(Playlist::size))
                .orElse(new Playlist())
                .size();
    }

    private Integer getHighestSongRating(List<Playlist> playlists) {
        return playlists.stream()
                .flatMap(playlist -> playlist.getSongs().stream())
                .max(Comparator.comparing(UserSong::getRank)).orElse(new UserSong())
                .getRank();
    }

    public void setPlaylistForUser(final Long userId, final Collection<Long> songIds) {
        LOG.info("setPlaylistForUser(" + userId + "," + songIds + ")");
        User user = userRepository.findOne(userId);
        Validate.notNull(user, "userId %d not found", userId);
        Iterable<Song> songs = this.songRepository.findAll(songIds);
        Playlist playlist = user.getPlaylist();
        playlist.reset();

        Integer positionIndex = 0;
        for (Song song : songs) {
            UserSong userSong = new UserSong(song, positionIndex++);
            userSongRepository.save(userSong);
            playlist.add(userSong);
        }
        playlistRepository.save(playlist);
    }

    public Playlist createEmptyPlaylist() {
        Playlist playlist = playlistRepository.save(new Playlist());
        LOG.info("new Playlist created with ID: " + playlist.getId());
        return playlist;
    }
}
