package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.dtos.PlaylistSongSummaryDto;
import com.crio.jukebox.dtos.UserPlaylistSummaryDto;
import com.crio.jukebox.entities.Playlist;

public interface IPlaylistService {
    public Playlist create(String id, String playlistName, List<String> listOfSongs);
    public void deletePlaylist(String userId, String playlistID);
    public UserPlaylistSummaryDto playPlaylist(String userID, String playlistID);
    public PlaylistSongSummaryDto addSongsToPlaylist(String userID, String playlistID, List<String> songIDs);
    public PlaylistSongSummaryDto deleteSongsFromPlaylist(String userID, String playlistID, List<String> songIDs);
    public PlaylistSongSummaryDto playSong(String userID, String songID);
    public PlaylistSongSummaryDto playNext(String userID);
    public PlaylistSongSummaryDto playPrevious(String userID);
    public Integer getSongPointer();
}
