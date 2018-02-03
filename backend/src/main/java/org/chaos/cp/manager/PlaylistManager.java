package org.chaos.cp.manager;

import org.chaos.cp.entity.Playlist;
import org.chaos.cp.entity.Song;
import org.chaos.cp.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistManager {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Transactional
    public List<Song> getMasterPlaylist() {
        List<Playlist> allPlaylists = new ArrayList<>();

        Long songsCount = 0L;
        List<Song> result = new ArrayList<>();
        for (Playlist playlist : playlistRepository.findAll()) {
            songsCount += playlist.size();
            allPlaylists.add(playlist);
        }

        Integer playlistIndex = 0;
        Integer playlistSongIndex = 0;
        for (int index = 0; index < songsCount; index++) {
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
}
