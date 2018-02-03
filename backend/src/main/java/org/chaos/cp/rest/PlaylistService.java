package org.chaos.cp.rest;

import org.chaos.cp.entity.Playlist;
import org.chaos.cp.entity.Song;
import org.chaos.cp.manager.PlaylistManager;
import org.chaos.cp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/playlist")
public class PlaylistService {

    @Autowired
    private PlaylistManager playlistManager;

    @Autowired
    private UserRepository users;

    @RequestMapping(path = "/overall", method = RequestMethod.GET)
    public @ResponseBody
    List<Song> getCommunityPlaylist() {
        return playlistManager.getMasterPlaylist();
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Playlist getUserPlaylist(@PathVariable(name = "userId") Long userId) {
        return users.findOne(userId).getPlaylist();
    }

}
