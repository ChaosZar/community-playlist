package org.chaos.cp.view;

import org.chaos.cp.UserSession;
import org.chaos.cp.connector.ServerConnector;
import org.chaos.cp.connector.json.Song;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class PersonalPlaylist {

    @Autowired
    private ServerConnector serverConnector;

    @Autowired
    private UserSession userSession;

    private String searchText;
    private List<Song> searchResult = new ArrayList<>();
    private List<Song> userPlaylist = new ArrayList<>();
    private Song selectedSong;
    private String save;

    public String getSearchText() {
        return searchText;
    }

    public void setServerConnector(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setSearchResult(List<Song> searchResult) {
        this.searchResult = searchResult;
    }

    public List<Song> getSearchResult() {
        return searchResult;
    }

    public void doSearch() {
        searchResult = serverConnector.search(searchText);
    }

    public void setUserPlaylist(List<Song> userPlaylist) {
        this.userPlaylist = userPlaylist;
    }

    public List<Song> getUserPlaylist() {
        return userPlaylist;
    }

    public void addSong() {
        userPlaylist.add(selectedSong);
    }

    public void setSelectedSong(Song selectedSong) {
        userPlaylist.add(selectedSong);
    }

    public Song getSelectedSong() {
        return selectedSong;
    }

    public void save() {
        serverConnector.savePlaylist(userSession.getUser().getId(), userPlaylist);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/overview.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
