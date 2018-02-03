package org.chaos.cp.rest;

import org.chaos.cp.entity.Song;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Controller
@RequestMapping("/song")
public class SongService {

    @RequestMapping(path="/name", method = RequestMethod.GET)
    public @ResponseBody
    List<Song> findSongsByName(@RequestParam(value = "name", defaultValue = "") String name) {
        throw new NotImplementedException();
    }

    @RequestMapping(path="/artist/{name}", method = RequestMethod.GET)
    public @ResponseBody
    List<Song> findSongsByArtist(@RequestParam(value = "name", defaultValue = "") String name) {
        throw new NotImplementedException();
    }

}
