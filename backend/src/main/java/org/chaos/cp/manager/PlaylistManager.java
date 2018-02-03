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
        List<Playlist> allPlaylists = new ArrayList<>();

        Long songsCount = 0L;
        List<UserSong> result = new ArrayList<>();
        for (Playlist playlist : playlistRepository.findAll()) {
            songsCount += playlist.size();
            allPlaylists.add(playlist);
        }

        Integer playlistIndex = 0;
        Integer playlistSongIndex = 0;
        for (int songCounter = 0; songCounter < songsCount; songCounter++) {
            if (playlistIndex == allPlaylists.size()) {
                playlistIndex = 0;
                playlistSongIndex++;
            }
            if (playlistSongIndex < allPlaylists.get(playlistIndex).size()) {
                result.add(allPlaylists.get(playlistIndex).get(playlistSongIndex));
            }
            playlistIndex++;
        }

        return result;
    }

    private Integer getHighestSongRating(List<Playlist> playlists) {
        return playlists.stream().flatMap(playlist -> playlist.getSongs().stream()).max(Comparator.comparing(UserSong::getRank)).get().getRank();
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
