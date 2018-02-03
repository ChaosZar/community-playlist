package org.chaos.cp.rest;

import org.chaos.cp.entity.Playlist;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Controller
@RequestMapping("/playlist")
public class PlaylistService {

    @RequestMapping(path = "/overall", method = RequestMethod.GET)
    public @ResponseBody
    Playlist getCommunityPlaylist() {
        throw new NotImplementedException();
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Playlist getUserPlaylist(@PathVariable(name = "userId") Long userId) {
        throw new NotImplementedException();
    }

}