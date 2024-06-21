package service;

import model.User;
import repository.impl.UserRepositoryImpl;

public interface UserService {
    UserRepositoryImpl userRepo=new UserRepositoryImpl();

    boolean register(User user);
    boolean login(String username,String password);
    User getUserProfile(String userId);
}
