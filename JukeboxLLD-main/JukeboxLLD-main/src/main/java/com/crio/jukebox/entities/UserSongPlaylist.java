package com.crio.jukebox.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserSongPlaylist {
    private Map<Playlist, List<Song>> playlistSongMap;

    public UserSongPlaylist(){
        this.playlistSongMap = new HashMap<Playlist, List<Song>>();
    }

    public UserSongPlaylist(Map<Playlist, List<Song>> playlistSongMap){
        this.playlistSongMap = playlistSongMap;
    }

    public void addSongsPlaylist(Playlist playlist, List<Song> songs){
        playlistSongMap.put(playlist, songs);
    }

    public List<Song> getSongsPlaylist(Playlist playlist){
        return playlistSongMap.get(playlist);
    }

    @Override
    public String toString() {
        return "UserSongPlaylist [playlistSongMap=" + playlistSongMap + "]";
    }
}
