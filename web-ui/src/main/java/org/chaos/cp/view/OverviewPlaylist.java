package org.chaos.cp.view;

import org.chaos.cp.UserSession;
import org.chaos.cp.connector.ServerConnector;
import org.chaos.cp.connector.json.UserSong;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.List;

@ManagedBean
public class OverviewPlaylist {

    public static String PATH = "/overview.xhtml";

    @Autowired
    private ServerConnector serverConnector;

    @Autowired
    private UserSession userSession;

    public List<UserSong> getMasterPlaylist() {
        return serverConnector.getMasterPlaylist();
    }


    public void goToMyMusic() {
        FacesUtils.redirect(PersonalPlaylist.PATH);
    }

    public void setAddRating(UserSong userSong) {
        serverConnector.incrementRanking(userSession.getUser(), userSong);
    }
}
