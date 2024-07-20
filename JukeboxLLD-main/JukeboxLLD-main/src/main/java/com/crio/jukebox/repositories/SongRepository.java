package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository{
    private HashMap<String, Song> songIdMap;

    public SongRepository(){
        this.songIdMap = new HashMap<>();
    }
    @Override
    public Song save(Song entity) {
        songIdMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Song> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Song findSongByGivenId(String id) {
        return songIdMap.get(id);
    }
}
