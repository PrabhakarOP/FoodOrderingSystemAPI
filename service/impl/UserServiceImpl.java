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
        if(userRepo.findByEmail(user.getEmail())!=null){
            Message.message="User already exist";
            return false;
        }
        userRepo.save(user);
        return true;
    }

    public User login(String email, String password) {
        User user=userRepo.findByEmail(email);
        if(user==null) {
            Message.message="User not found";
            return null;
        }

        if(user.getPassword().equals(password))
            return user;

        Message.message="!!!Wrong password!!!";
        return null;

    }

    public User getUserProfile(String userId) {
        return userRepo.findByUserId(userId);
    }
}
