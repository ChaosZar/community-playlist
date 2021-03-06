package org.chaos.cp.rest;

import org.chaos.cp.entity.Playlist;
import org.chaos.cp.entity.Song;
import org.chaos.cp.manager.PlaylistManager;
import org.chaos.cp.repository.UserRepository;
import org.chaos.cp.rest.json.MasterPlaylistResponse;
import org.chaos.cp.rest.json.SetPlaylistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("/playlist")
public class PlaylistService {

    @Autowired
    private PlaylistManager playlistManager;

    @Autowired
    private UserRepository users;

    @RequestMapping(path = "/overall", method = RequestMethod.GET)
    public @ResponseBody
    MasterPlaylistResponse getCommunityPlaylist() {
        return new MasterPlaylistResponse(playlistManager.getMasterPlaylist());
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Playlist getUserPlaylist(@PathVariable(name = "userId") Long userId) {
        return users.findOne(userId).getPlaylist();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void setPlaylistForUser(@RequestBody SetPlaylistRequest setPlaylistRequest) {
        playlistManager.setPlaylistForUser(setPlaylistRequest.getUserId(),
                setPlaylistRequest.getSongs().stream().map(Song::getId).collect(Collectors.toList()));
    }

}
