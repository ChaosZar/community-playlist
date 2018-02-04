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

            User user = new User(userName);
            user.setPlaylist(emptyPlaylist);
            byLogin = userRepository.save(user);
        }
        return byLogin;
    }
}
