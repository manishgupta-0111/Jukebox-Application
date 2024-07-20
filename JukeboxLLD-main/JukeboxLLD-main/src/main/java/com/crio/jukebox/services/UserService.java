package com.crio.jukebox.services;


import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.IUserRepository;

public class UserService implements IUserService{
    private IUserRepository userRepository;
    private IPlaylistRepository playlistRepository;


    public UserService(IUserRepository userRepository, IPlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }
    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @Override
    public User create(String name) {
        User user = new User(name);
        return userRepository.save(user);
    }
    
    
}
