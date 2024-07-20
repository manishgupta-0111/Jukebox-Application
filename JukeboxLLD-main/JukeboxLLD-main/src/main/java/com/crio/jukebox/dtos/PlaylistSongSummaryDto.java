package com.crio.jukebox.dtos;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public class PlaylistSongSummaryDto {
    private Playlist playList;
    private List<Song> songList;

    
    public PlaylistSongSummaryDto(Playlist playList, List<Song> songList) {
        this.playList = playList;
        this.songList = songList;
    }
    public Playlist getPlayList() {
        return playList;
    }
    public void setPlayList(Playlist playList) {
        this.playList = playList;
    }
    public List<Song> getSongList() {
        return songList;
    }
    public void setSongList(List<Song> songList) {
        this.songList = songList;
    } 
    public Song getSong(String id){
        for(Song song : songList){
            if(song.getId().equals(id))
                return song;
        }
        return null;
    }
}
