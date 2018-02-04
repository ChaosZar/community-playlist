package org.chaos.cp.manager;

import org.chaos.cp.entity.Playlist;
import org.chaos.cp.entity.User;
import org.chaos.cp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {
    private static final Logger LOG = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaylistManager playlistManager;

    public User getUser(String userName) {
        User byLogin = userRepository.findByLogin(userName);
        if (byLogin == null) {
            LOG.info("user not found, will create new one with name: " + userName);
            Playlist emptyPlaylist = playlistManager.createEmptyPlaylist();

            User user = new User(userName);
            user.setPlaylist(emptyPlaylist);
            byLogin = userRepository.save(user);
        }
        LOG.info("User will be returned with following ID: " + byLogin.getId());
        return byLogin;
    }
}
