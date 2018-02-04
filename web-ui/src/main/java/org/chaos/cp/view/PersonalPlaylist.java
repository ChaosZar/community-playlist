package org.chaos.cp.view;

import org.chaos.cp.connector.ServerConnector;
import org.chaos.cp.connector.json.Song;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class PersonalPlaylist {

    @Autowired
    private ServerConnector serverConnector;

    private String searchText;
    private List<Song> searchResult = new ArrayList<>();

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
}
