package org.chaos.cp.rest;

import org.chaos.cp.connector.MusicStorage;
import org.chaos.cp.entity.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@CrossOrigin
@RequestMapping("/song")
public class SongService {
    private static final Logger LOG = LoggerFactory.getLogger(SongService.class);

    @Autowired
    private List<MusicStorage> musicStorages;

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public @ResponseBody
    List<Song> findSongsByName(@PathVariable(value = "name") String name) {
        return getEnabledMusicStorages()
                .flatMap(musicStorage -> {
                    Collection<Song> songs;
                    try {
                        songs = musicStorage.findSongsByTitle(name);
                    } catch (IOException e) {
                        LOG.error("Error while finding songs", e);
                        songs = Collections.emptyList();
                    }
                    return songs.stream();
                }).collect(Collectors.toList());
    }

    private Stream<MusicStorage> getEnabledMusicStorages() {
        return musicStorages.stream().filter(MusicStorage::isEnabled);
    }

    @RequestMapping(path = "/artist/{name}", method = RequestMethod.GET)
    public @ResponseBody
    List<Song> findSongsByArtist(@PathVariable(value = "name") String name) {
        return getEnabledMusicStorages().flatMap(musicStorage -> {
            Collection<Song> songs;
            try {
                songs = musicStorage.findSongsByArtist(name);
            } catch (IOException e) {
                LOG.error("Error while finding songs", e);
                songs = Collections.emptyList();
            }
            return songs.stream();
        }).collect(Collectors.toList());
    }

    @RequestMapping(path = "/stream/{songId}", method = RequestMethod.GET)
    @ResponseBody
    public Resource getSong(@PathVariable(value = "songId") Long songId) {
        Collection<MusicStorage> enabledMusicStorages =
                getEnabledMusicStorages().collect(Collectors.toList());
        Song song = null;
        for (MusicStorage musicStorage : enabledMusicStorages) {
            try {
                song = musicStorage.getSong(songId);
            } catch (IOException e) {
                LOG.error("Error while getting song {}", songId, e);
            }
            // TODO check for type and only return FileSystemResource if it's local song
            if (song != null) {
                return new FileSystemResource(song.getRef());
            }
        }
        throw new IllegalArgumentException("invalid song " + songId);
    }
}
