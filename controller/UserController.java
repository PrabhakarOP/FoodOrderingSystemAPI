package controller;

import model.User;
import service.impl.UserServiceImpl;

public class UserController {
    UserServiceImpl userService= UserServiceImpl.getInstance();

    boolean register(User user){
        return userService.register(user);
    }
    boolean login(String username,String password){
        return userService.login(username,password);
    }
    User getUserProfile(String userId){
        return userService.getUserProfile(userId);
    }
}
