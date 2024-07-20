package com.crio.jukebox.entities;

public class Song extends BaseEntity{
    private String songName;
    private String genre;
    private String albumName;
    private Status status;
    private String artistName;
    private String featuredArtist;

    public Song(Song song){
        this.songName = song.getSongName();
        this.artistName = song.getArtistName();
        this.albumName = song.getAlbumName();
        this.genre = song.getGenre();
        this.status = song.getStatus();
    }

    public Song(String id, String songName, String genre, String albumName, String artistName, String featuredArtist){
        this.id = id;
        this.songName = songName;
        this.genre = genre;
        this.albumName = albumName;
        this.artistName = artistName;
        this.status = Status.NOT_PLAYING;
        this.featuredArtist = featuredArtist;
    }

    public Song(String id, String songName, Genre genre, String albumName, String artistName, String featuredArtist, Status status){
        this(id, songName);
        this.status = Status.NOT_PLAYING;
    }

    public Song(String id, String songName){
        this.id = id;
        this.songName = songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSongName(){
        return songName; 
    }

    public String getArtistName(){
        return artistName; 
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getFeaturedArtist() {
        return featuredArtist;
    }

    public void setFeaturedArtist(String featuredArtist) {
        this.featuredArtist = featuredArtist;
    }

}
