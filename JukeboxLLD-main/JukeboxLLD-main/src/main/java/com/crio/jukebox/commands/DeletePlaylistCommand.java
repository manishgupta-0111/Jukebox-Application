package com.crio.jukebox.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.services.IPlaylistService;
import com.opencsv.exceptions.CsvValidationException;

public class DeletePlaylistCommand implements ICommand{
    private IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService playlistService){
        this.playlistService = playlistService;
    }
    @Override
    public void execute(List<String> tokens) throws CsvValidationException, IOException {
        String command = tokens.get(0);
        if(command.equals("DELETE-PLAYLIST")){
            String userID = tokens.get(1);
            String playlistID = tokens.get(2);
            playlistService.deletePlaylist(userID, playlistID);
            System.out.println("Delete Successful");
        }
    }
}
