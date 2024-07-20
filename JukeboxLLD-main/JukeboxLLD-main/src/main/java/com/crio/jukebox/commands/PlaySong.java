package com.crio.jukebox.commands;

import java.io.IOException;
import java.util.List;
import com.crio.jukebox.dtos.PlaylistSongSummaryDto;
import com.crio.jukebox.services.IPlaylistService;
import com.opencsv.exceptions.CsvValidationException;

public class PlaySong implements ICommand{
    private IPlaylistService playlistService;

    public PlaySong(IPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) throws CsvValidationException, IOException {
        String userID = tokens.get(1);
        String value = tokens.get(2);
        if(value.equals("NEXT")){
            PlaylistSongSummaryDto playlistSongSummaryDto = playlistService.playNext(userID);
            Integer pointer = playlistService.getSongPointer();

            String songName = playlistSongSummaryDto.getPlayList().getSongs().get(pointer - 1).getSongName();
            String albumName = playlistSongSummaryDto.getPlayList().getSongs().get(pointer - 1).getAlbumName();
            String artists = playlistSongSummaryDto.getPlayList().getSongs().get(pointer - 1).getFeaturedArtist().replace("#", ",") ;

            System.out.println("Current Song Playing");
            System.out.println("Song - " +songName);
            System.out.println("Album - " +albumName);
            System.out.println("Artists - " + artists);
            
        }else if(value.equals("BACK")){
            PlaylistSongSummaryDto playlistSongSummaryDto = playlistService.playPrevious(userID);
            Integer pointer = playlistService.getSongPointer();

            String songName = playlistSongSummaryDto.getPlayList().getSongs().get(pointer - 1).getSongName();
            String albumName = playlistSongSummaryDto.getPlayList().getSongs().get(pointer - 1).getAlbumName();
            String artists = playlistSongSummaryDto.getPlayList().getSongs().get(pointer - 1).getFeaturedArtist().replace("#", ",") ;

            System.out.println("Current Song Playing");
            System.out.println("Song - " +songName);
            System.out.println("Album - " +albumName);
            System.out.println("Artists - " + artists);
        }else{
            PlaylistSongSummaryDto playlistSongSummaryDto = playlistService.playSong(userID, value);
            if(playlistSongSummaryDto == null){
                System.out.println("Given song id is not a part of the active playlist");
            }else{
                String songName = playlistSongSummaryDto.getSong(value).getSongName();
                String albumName = playlistSongSummaryDto.getSong(value).getAlbumName();
                String artists = playlistSongSummaryDto.getSong(value).getFeaturedArtist().replace("#", ",");

                System.out.println("Current Song Playing");
                System.out.println("Song - " +songName);
                System.out.println("Album - " +albumName);
                System.out.println("Artists - " + artists);
            }
        }
    }
    
}
