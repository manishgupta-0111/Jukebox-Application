package com.crio.jukebox.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.dtos.PlaylistSongSummaryDto;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IPlaylistService;
import com.opencsv.exceptions.CsvValidationException;

public class ModifyPlaylist implements ICommand{
    private IPlaylistService playlistService;
    
    public ModifyPlaylist(IPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) throws CsvValidationException, IOException {
        String subCommand = tokens.get(1);
        String userID = tokens.get(2);
        String playlistID = tokens.get(3);
        List<String> songIDs = new ArrayList<>();
        for(int i = 4; i < tokens.size(); i++)
            songIDs.add(tokens.get(i));
        if(subCommand.equals("ADD-SONG")){
            PlaylistSongSummaryDto playlistSongSummaryDto = playlistService.addSongsToPlaylist(userID, playlistID, songIDs);
            
            String printPlaylistID = playlistSongSummaryDto.getPlayList().getId();
            String printPlaylistName = playlistSongSummaryDto.getPlayList().getPlaylistName();
            String printSongIDs = "";
            List<Song> songList = playlistSongSummaryDto.getSongList();
            for(Song song : songList){
                //printSongIDs += song.getId() + " ";
                printSongIDs = printSongIDs + " " + song.getId();
            }
            String printSongIds = printSongIDs.replace(" - ", " -");
            System.out.println("Playlist ID - " + printPlaylistID);
            System.out.println("Playlist Name - " + printPlaylistName);
            System.out.println("Song IDs -" + printSongIds);

        }
        if(subCommand.equals("DELETE-SONG")){
            PlaylistSongSummaryDto playlistSongSummaryDto = playlistService.deleteSongsFromPlaylist(userID, playlistID, songIDs);
            String printPlaylistID = playlistSongSummaryDto.getPlayList().getId();
            String printPlaylistName = playlistSongSummaryDto.getPlayList().getPlaylistName();
            String printSongIDs = "";
            List<Song> songList = playlistSongSummaryDto.getSongList();
            for(Song song : songList){
                //printSongIDs += song.getId() + " " + "";
                printSongIDs = printSongIDs + " " + song.getId();
            }
            String printSongIds = printSongIDs.replace(" - ", " -");
            System.out.println("Playlist ID - " + printPlaylistID);
            System.out.println("Playlist Name - " + printPlaylistName);
            System.out.println("Song IDs -" + printSongIds);
        }
    }

}
