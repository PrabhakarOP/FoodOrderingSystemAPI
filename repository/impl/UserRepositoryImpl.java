package repository.impl;

import model.User;
import repository.UserRepository;

import java.util.ArrayList;

public class UserRepositoryImpl implements UserRepository {
    //singleton design patter
    private static UserRepositoryImpl instance;

    public static synchronized UserRepositoryImpl getInstance(){
        if(instance==null)
            instance=new UserRepositoryImpl();
        return instance;
    }

    public void save(User user) {
        userList.add(user);
    }

    public User findByUsername(String username) {
        for(User user: userList){
            if(user.getUsername().equalsIgnoreCase(username))
                return user;
        }
        return null;
    }

    public User findByEmail(String email) {
        for(User user: userList){
            if(user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    public User findByUserId(String userId) {
        for(User user: userList){
            if(user.getId().equals(userId))
                return user;
        }
        return null;
    }
}
