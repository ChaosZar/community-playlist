package org.chaos.cp.rest;

import org.chaos.cp.entity.Playlist;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Playlist getUserPlaylist(@RequestParam(name = "userId") Long userId) {
        throw new NotImplementedException();
    }

}
