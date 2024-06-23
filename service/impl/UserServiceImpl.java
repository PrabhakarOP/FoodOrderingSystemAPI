package service.impl;

import helper.Message;
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

    public boolean login(String email, String password) {
        User user=userRepo.findByEmail(email);
        if(user==null) {
            Message.message="User not found";
            return false;
        }
        Message.message="!!!Wrong email or password!!!";
        return user.getPassword().equals(password);

    }

    public User getUserProfile(String userId) {
        return userRepo.findByUserId(userId);
    }
}
