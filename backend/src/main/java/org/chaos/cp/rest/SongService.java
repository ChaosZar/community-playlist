package org.chaos.cp.rest;

import org.chaos.cp.entity.Song;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Controller
@RequestMapping("/song")
public class SongService {

    @RequestMapping(path = "/name/{name}", method = RequestMethod.GET)
    public @ResponseBody
    List<Song> findSongsByName(@PathVariable(value = "name") String name) {
        throw new NotImplementedException();
    }

    @RequestMapping(path = "/artist/{name}", method = RequestMethod.GET)
    public @ResponseBody
    List<Song> findSongsByArtist(@PathVariable(value = "name") String name) {
        throw new NotImplementedException();
    }

}
