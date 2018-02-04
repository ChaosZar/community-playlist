package org.chaos.cp.manager;

import org.chaos.cp.entity.Playlist;
import org.chaos.cp.entity.User;
import org.chaos.cp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaylistManager playlistManager;


    public User getUser(String userName) {
        User byLogin = userRepository.findByLogin(userName);
        if (byLogin == null) {
            Playlist emptyPlaylist = playlistManager.createEmptyPlaylist();

            byLogin = userRepository.save(new User(userName));
            byLogin.setPlaylist(emptyPlaylist);
        }
        return byLogin;
    }
}
