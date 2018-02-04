package org.chaos.cp.view;

import org.chaos.cp.UserSession;
import org.chaos.cp.connector.ServerConnector;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
public class Login {

    @Autowired
    private ServerConnector serverConnector;

    @Autowired
    private UserSession userSession;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void doLogin() {
        userSession.setUser(serverConnector.getUser(username));
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/overview.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
