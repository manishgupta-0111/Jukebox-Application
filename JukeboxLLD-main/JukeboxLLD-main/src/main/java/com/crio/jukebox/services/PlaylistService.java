package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.dtos.PlaylistSongSummaryDto;
import com.crio.jukebox.dtos.UserPlaylistSummaryDto;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.Status;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class PlaylistService implements IPlaylistService{
    private IPlaylistRepository playlistRepository;
    private ISongRepository songRepository;
    private IUserRepository userRepository;
    private Integer songPointer = 1;

    public PlaylistService(IPlaylistRepository playlistRepository,  ISongRepository songRepository, IUserRepository userRepository){
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    public PlaylistService(IPlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }
    @Override
    public Playlist create(String id, String playlistName, List<String> listOfSongs) {
        List<Song> songs = new ArrayList<>();
        
        //Searching for the songs from the song repo with their id then adding them to a temporary list:
        for(String songNumber : listOfSongs){
            Song song = songRepository.findSongByGivenId(songNumber);
            songs.add(song);
        }

        // This is the user that is creatidng the playlist.
        User user = userRepository.findUserById(id);
        //Creating a new playlist.
        Playlist playList = new Playlist(id, playlistName, songs);

        //Saved the whole playlist to the repo.
        Playlist savedPlaylist = playlistRepository.save(playList);

        // Mapped the playlist to the user-id.
        user.addPlaylist(savedPlaylist);

        return savedPlaylist;
    }

    @Override
    public void deletePlaylist(String userID, String playlistID) {
        User user = userRepository.findUserById(userID);
        Playlist playlist = playlistRepository.findPlaylistById(playlistID);
        user.deletePlaylist(playlist);
        playlistRepository.deleteById(playlistID);
    }

    @Override
    public PlaylistSongSummaryDto addSongsToPlaylist(String userID, String playlistID, List<String> songIDs) {
        User user = userRepository.findUserById(userID);
        Playlist playlist = playlistRepository.findPlaylistById(playlistID);
        List<Song> songsRequested  = new ArrayList<>();

        //Deleting the old record of the playlist present with the user. 
        deletePlaylist(user.getId(), playlistID);

        //Songs requested are stored in songsRequested.
        for(String songId : songIDs){
            Song songToBeAdded = songRepository.findSongByGivenId(songId);
            songsRequested.add(songToBeAdded);
        }

        //Adding the songs requested to the user's playlist: 
        List<Song> songsAlreadyInPlayList = playlist.getSongs();
        for(Song songs : songsRequested){
            if(!songsAlreadyInPlayList.contains(songs)){
                playlist.addSong(songs);
            }
        }
        //Saving it back into the repo:
        playlistRepository.save(playlist);
        
        PlaylistSongSummaryDto playlistSongSummaryDto = new PlaylistSongSummaryDto(playlist, playlist.getSongs());
        user.addPlaylist(playlist);
        return playlistSongSummaryDto;
    }

    @Override
    public PlaylistSongSummaryDto deleteSongsFromPlaylist(String userID, String playlistID, List<String> songIDs) {
        User user = userRepository.findUserById(userID);
        Playlist playlist = playlistRepository.findPlaylistById(playlistID);
        List<Song> songsNeedToBeRemoved  = new ArrayList<>();

        deletePlaylist(user.getId(), playlistID);

        //Songs that needed to removed are stored in songsNeedToBeRemoved
        for(String songId : songIDs){
            Song songToBeRemoved = songRepository.findSongByGivenId(songId);
            songsNeedToBeRemoved.add(songToBeRemoved);
        }

        //Removing the songs requested to the user's playlist: 
        List<Song> songsAlreadyInPlayList = playlist.getSongs();
        for(Song songs : songsNeedToBeRemoved){
            if(songsAlreadyInPlayList.contains(songs)){
                playlist.deleteSong(songs);
            }
        }
        //Saving it back into the repo: 
        playlistRepository.save(playlist);
        PlaylistSongSummaryDto playlistSongSummaryDto = new PlaylistSongSummaryDto(playlist, playlist.getSongs());
        user.addPlaylist(playlist);
        return playlistSongSummaryDto;
    }

    @Override
    public UserPlaylistSummaryDto playPlaylist(String userID, String playlistID) {
        User user = userRepository.findUserById(userID);
        Playlist playList = playlistRepository.findPlaylistById(playlistID);
        deletePlaylist(userID, playlistID);
        playList.setActive(1);
        playlistRepository.save(playList);
        UserPlaylistSummaryDto userPlaylistSummaryDto = new UserPlaylistSummaryDto(user, playList);
        return userPlaylistSummaryDto;
    }

    @Override
    public PlaylistSongSummaryDto playSong(String userID, String songID) {
        songPointer = 1;
        //To get the user current user.
        User user = userRepository.findUserById(userID);
        //To get all the playlist. 
        List<Playlist> playlists = user.getPlaylist();

        //To find the playlist which is active.
        Playlist currentlyActivePlaylist = currentlyActive(playlists);

        //Must check if the currently active playlist has the song: 
        List<Song> playlistSongs = currentlyActivePlaylist.getSongs();
        for(Song song: playlistSongs){
            if(song.getId().equals(songID)){
                song.setStatus(Status.PLAYING);
                PlaylistSongSummaryDto playerPlaylistSongSummaryDto = new PlaylistSongSummaryDto(currentlyActivePlaylist, playlistSongs);
                return playerPlaylistSongSummaryDto;
            }
            songPointer++;
        }
        return null;
    }

    public Playlist currentlyActive(List<Playlist> playlists){
        for(Playlist playlist : playlists){
            if(playlist.getActive() == 1)
                return playlist;
        }
        //Must write code for inactive playlist here.
        return null;
    }

    @Override
    public PlaylistSongSummaryDto playNext(String userID) {
        User user = userRepository.findUserById(userID);
        //To get all the playlist. 
        List<Playlist> playlists = user.getPlaylist();

        //To find the playlist which is active.
        Playlist currentlyActivePlaylist = currentlyActive(playlists);
        currentlyActivePlaylist.getSongs().get(songPointer-1).setStatus(Status.NOT_PLAYING);
        songPointer++;
        //Play the first song if the next command is done on the last song.
        if(songPointer > currentlyActivePlaylist.getSongs().size()){
            String id = currentlyActivePlaylist.getSongs().get(0).getId();
            return playSong(userID, id);
        }else{
            currentlyActivePlaylist.getSongs().get(songPointer-1).setStatus(Status.PLAYING);
            PlaylistSongSummaryDto playerPlaylistSongSummaryDto = new PlaylistSongSummaryDto(currentlyActivePlaylist, currentlyActivePlaylist.getSongs());
            return playerPlaylistSongSummaryDto;
        }
    }

    @Override
    public PlaylistSongSummaryDto playPrevious(String userID) {
        User user = userRepository.findUserById(userID);
        //To get all the playlist. 
        List<Playlist> playlists = user.getPlaylist();

        //To find the playlist which is active.
        Playlist currentlyActivePlaylist = currentlyActive(playlists);
        currentlyActivePlaylist.getSongs().get(songPointer-1).setStatus(Status.NOT_PLAYING);
        songPointer--;
        //Play the first song if the next command is done on the last song.
        if(songPointer == 0){
            int size = currentlyActivePlaylist.getSongs().size();
            String id = currentlyActivePlaylist.getSongs().get(size - 1).getId();
            return playSong(userID, id);
        }else{
            currentlyActivePlaylist.getSongs().get(songPointer).setStatus(Status.PLAYING);
            PlaylistSongSummaryDto playerPlaylistSongSummaryDto = new PlaylistSongSummaryDto(currentlyActivePlaylist, currentlyActivePlaylist.getSongs());
            return playerPlaylistSongSummaryDto;
        }
    }

    public Integer getSongPointer() {
        return songPointer;
    }
}
