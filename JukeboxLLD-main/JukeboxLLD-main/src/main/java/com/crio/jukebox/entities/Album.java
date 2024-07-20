package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class Album extends BaseEntity{
    private String name;
    private List<Song> songs;
    private String artistName;
    private List<Artist> featuredArtists;

    public Album(String id, String albumName){
        this.name = albumName;
        this.id = id;
        this.songs = new ArrayList<>();
        this.featuredArtists = new ArrayList<>();
    }
    
    public Album(String name, List<Song> songs, String artistName, List<Artist> featuredArtists) {
        this.name = name;
        this.songs = songs;
        this.artistName = artistName;
        this.featuredArtists = featuredArtists;
    }
    public List<Artist> getFeaturedArtists() {
        return featuredArtists;
    }
    public void setFeaturedArtists(List<Artist> featuredArtists) {
        this.featuredArtists = featuredArtists;
    }
    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
