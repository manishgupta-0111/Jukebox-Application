package com.crio.jukebox.entities;

import java.util.List;

public class Playlist extends BaseEntity{
    private String playlistName;
    private List<Song> songs;
    private String playlistId;
    private Integer active;
    
    public Playlist(Playlist newPlaylist){
        this.id = newPlaylist.getId();
        this.playlistName = newPlaylist.getPlaylistName();
        this.songs = newPlaylist.getSongs();
        this.active = 0;
    }

    public Playlist(String playlistID, String id, String playlistName, List<Song> songs) {
        this(id, playlistName, songs);
        this.playlistId = playlistID;
        this.active = 0;
    }
    public Playlist(String id, String playlistName, List<Song> songs) {
        this.id = id;
        this.playlistName = playlistName;
        this.songs = songs;
    }
    public String getPlaylistName() {
        return playlistName;
    }
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    public String getPlaylistId() {
        return playlistId;
    }
    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
    public void addSong(Song song){
        songs.add(song);
    }
    public void deleteSong(Song song){
        songs.remove(song);
    }
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
    
}
