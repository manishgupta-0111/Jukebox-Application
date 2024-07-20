package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public class PlaylistRepository implements IPlaylistRepository{
    private final Map<String, Playlist> playlistMap;
    private Integer playlistID = 0;
    //private Integer autoIncrement = 0;

    public PlaylistRepository(){
        this.playlistMap = new HashMap<>();
    }

    @Override
    public Playlist save(Playlist entity) {
        if(entity.getPlaylistId() == null){
            playlistID++;
            String playlistIDInString = String.valueOf(playlistID);
            String id = entity.getId();
            String playlistName = entity.getPlaylistName();
            List<Song> songs = new ArrayList<>();
            for(Song song : entity.getSongs()){
                songs.add(song);
            }
            Playlist newPlaylist = new Playlist(playlistIDInString, id, playlistName, songs);
            playlistMap.put(playlistIDInString, newPlaylist);
            return newPlaylist;
        }
        return playlistMap.put(entity.getPlaylistId(), entity);
    }

    @Override
    public List<Playlist> findAll() {
        List<Playlist> playLists = new ArrayList<>(playlistMap.values());
        return playLists;
    }

    @Override
    public Optional<Playlist> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        playlistMap.remove(id);
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Playlist findPlaylistById(String playlistID) {
        return playlistMap.get(playlistID);
    }
}
