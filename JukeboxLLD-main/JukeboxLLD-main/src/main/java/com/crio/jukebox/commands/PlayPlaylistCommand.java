package com.crio.jukebox.commands;

import java.io.IOException;
import java.util.List;
import com.crio.jukebox.dtos.UserPlaylistSummaryDto;
import com.crio.jukebox.services.IPlaylistService;
import com.opencsv.exceptions.CsvValidationException;

public class PlayPlaylistCommand implements ICommand{
    private IPlaylistService playlistService;

    public PlayPlaylistCommand(IPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) throws CsvValidationException, IOException {
        String userID = tokens.get(1);
        String playlistID = tokens.get(2);
        UserPlaylistSummaryDto userPlaylistSummaryDto = playlistService.playPlaylist(userID, playlistID);

        String firstSong = userPlaylistSummaryDto.getPlayLists().getSongs().get(0).getSongName();
        String firstSongAlbum = userPlaylistSummaryDto.getPlayLists().getSongs().get(0).getAlbumName();
        String firstSongArtists = userPlaylistSummaryDto.getPlayLists().getSongs().get(0).getFeaturedArtist().replace('#', ',');

        System.out.println("Current Song Playing");
        System.out.println("Song - " + firstSong);
        System.out.println("Album - " + firstSongAlbum);
        System.out.println("Artists - " + firstSongArtists);
    }
    
}
