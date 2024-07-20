package com.crio.jukebox.services;

import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class LoadDataService implements ILoadDataService{

    private ISongRepository songRepository;

    public LoadDataService(ISongRepository songRepository){
        this.songRepository = songRepository;
    }

    @Override
    public void loadData(String path) throws CsvValidationException, IOException {
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] record;
            while ((record = reader.readNext()) != null) {

                Song song = new Song(record[0], record[1], record[2], record[3], record[4],  record[5]);
                //This should be a song-repository.
                songRepository.save(song);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
