package controller;

import model.User;
import service.impl.UserServiceImpl;

public class UserController {
    UserServiceImpl userService= UserServiceImpl.getInstance();

    //singleton design pattern
    private static UserController instance;

    public static synchronized UserController getInstance(){
        if(instance==null)
            instance=new UserController();
        return instance;
    }

    public boolean register(User user){
        return userService.register(user);
    }
    public User login(String email,String password){
        return userService.login(email,password);
    }
    public User getUserProfile(String userId){
        return userService.getUserProfile(userId);
    }
}
