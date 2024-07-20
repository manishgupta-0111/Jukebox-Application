package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository{
    private final Map<String,User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        this.userMap = new HashMap<>();
    }
    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public Optional<User> findByName(String name) {
        return findAll().stream()
                        .filter(user -> user.getName().equals(name))
                        .findFirst();
    }

    @Override
    public User save(User entity) {
        if(entity.getId() == null){
            autoIncrement++;
            User newUser = new User(Integer.toString(autoIncrement),entity.getName());
            userMap.put(Integer.toString(autoIncrement), newUser);
            return newUser;
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(User entity) {
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
    public User findUserById(String id) {
        return userMap.get(id);
    }
    
}
