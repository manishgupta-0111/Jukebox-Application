package com.crio.jukebox.dtos;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;

public class UserPlaylistSummaryDto {
    private User user; 
    private Playlist playLists;

    public UserPlaylistSummaryDto(User user, Playlist playLists) {
        this.user = user;
        this.playLists = playLists;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Playlist getPlayLists() {
        return playLists;
    }
    public void setPlayLists(Playlist playLists) {
        this.playLists = playLists;
    }
}
