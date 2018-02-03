package org.chaos.cp.rest;

import org.chaos.cp.connector.MusicStorage;
import org.chaos.cp.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/song")
public class SongService {

    @Autowired
    private List<MusicStorage> musicStorages;

    @RequestMapping(path = "/name/{name}", method = RequestMethod.GET)
    public @ResponseBody
    List<Song> findSongsByName(@PathVariable(value = "name") String name) {
        return musicStorages.stream().filter(MusicStorage::isEnabled)
                .flatMap(musicStorage -> {
                    Collection<Song> songs;
                    try {
                        songs = musicStorage.findSongsByTitle(name);
                    } catch (IOException e) {
                        e.printStackTrace();
                        songs = Collections.emptyList();
                    }
                    return songs.stream();
                }).collect(Collectors.toList());
    }

    @RequestMapping(path = "/artist/{name}", method = RequestMethod.GET)
    public @ResponseBody
    List<Song> findSongsByArtist(@PathVariable(value = "name") String name) {
        throw new NotImplementedException();
    }

}
