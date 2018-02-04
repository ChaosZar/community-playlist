package org.chaos.cp.view;

import org.chaos.cp.connector.ServerConnector;
import org.chaos.cp.connector.json.UserSong;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@ManagedBean
public class OverviewPlaylist {

    @Autowired
    private ServerConnector serverConnector;

    public List<UserSong> getMasterPlaylist() {
        return serverConnector.getMasterPlaylist();
    }

    public void goToMyMusic() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/personal.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
