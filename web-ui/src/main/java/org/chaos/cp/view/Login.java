package org.chaos.cp.view;

import org.chaos.cp.UserSession;
import org.chaos.cp.connector.ServerConnector;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

@ManagedBean
public class Login {

    public static String PATH = "/login.xhtml";

    @Autowired
    private ServerConnector serverConnector;

    @Autowired
    private UserSession userSession;

    private String username;


    @PostConstruct
    private void postConstruct() {
//        if (userSession.getUser() != null) {
//            FacesUtils.redirect(OverviewPlaylist.PATH);
//        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void doLogin() {
        userSession.setUser(serverConnector.getUser(username));
        FacesUtils.redirect(OverviewPlaylist.PATH);
    }
}
