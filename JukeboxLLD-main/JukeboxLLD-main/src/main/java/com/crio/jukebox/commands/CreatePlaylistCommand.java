package com.crio.jukebox.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistService;
import com.opencsv.exceptions.CsvValidationException;

public class CreatePlaylistCommand implements ICommand{
    private IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService playlistService){
        this.playlistService = playlistService;
    }
    @Override
    public void execute(List<String> tokens) throws CsvValidationException, IOException {
        String command = tokens.get(0);
        if(command.equals("CREATE-PLAYLIST")){
            String userId = tokens.get(1);
            String playListName = tokens.get(2);
            List<String> songIDs = new ArrayList<>();   
    
            //All the song id's are stored in songIDs.
            for(int i = 3; i < tokens.size(); i++){
                songIDs.add(tokens.get(i));
            }
            Playlist playlist = playlistService.create(userId, playListName, songIDs);
            System.out.println("Playlist ID - " + playlist.getPlaylistId());
        }
    }
    
}
