package service.impl;

import model.User;
import repository.impl.UserRepositoryImpl;
import service.UserService;

public class UserServiceImpl implements UserService {
    //singleton design pattern
    private static UserServiceImpl instance;

    public static synchronized UserServiceImpl getInstance(){
        if(instance==null)
            instance=new UserServiceImpl();
        return instance;
    }

    public boolean register(User user) {
        userRepo.save(user);
        return true;
    }

    public boolean login(String username, String password) {
        User user=userRepo.findByUsername(username);
        if(user==null)
            return false;

        else return user.getPassword().equals(password);

    }

    public User getUserProfile(String userId) {
        return userRepo.findByUserId(userId);
    }
}
