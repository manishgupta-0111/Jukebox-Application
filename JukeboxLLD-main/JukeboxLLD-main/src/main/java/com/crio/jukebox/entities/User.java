package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity {
    private final String name;
    private final List<Playlist> playlist;

    public User(User user){
        this(user.name, user.playlist);
    }

    public User(String newName){
        this.name = newName;
        this.playlist = new ArrayList<>();
        //this.userSongPlaylist = new UserSongPlaylist();
    }
    
    /*public User(String name, List<Playlist> playlist, UserSongPlaylist userSongPlaylist){
        this.name = name;
        this.playlist = playlist;
        this.userSongPlaylist = userSongPlaylist;
    }*/

    public User(String name, List<Playlist> playlist){
        this.name = name;
        this.playlist = playlist;
    }
    
    public User(String id, String userName) {
        this(userName);
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void addPlaylist(Playlist newPlaylist){
        playlist.add(newPlaylist);
    }
    
    public void deletePlaylist(Playlist currentPlaylist){
        playlist.remove(currentPlaylist);
    }
}
