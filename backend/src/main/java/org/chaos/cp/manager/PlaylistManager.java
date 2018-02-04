package org.chaos.cp.manager;

import org.chaos.cp.entity.Playlist;
import org.chaos.cp.entity.Song;
import org.chaos.cp.entity.User;
import org.chaos.cp.entity.UserSong;
import org.chaos.cp.repository.PlaylistRepository;
import org.chaos.cp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PlaylistManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Transactional
    public List<UserSong> getMasterPlaylist() {
        List<UserSong> result = new ArrayList<>();

        List<Playlist> allPlaylists = getAllPlaylistsAsList();

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

    public void setPlaylistForUser(final Long userId, final List<Song> songs) {
        User user = userRepository.findOne(userId);

        Playlist playlist = user.getPlaylist();
        playlist.reset();

        Integer positionIndex = 0;
        for (Song song : songs) {
            UserSong userSong = new UserSong(user, song, positionIndex++);
            playlist.add(userSong);
        }
    }
}
