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
