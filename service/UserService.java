package service;

import model.User;
import repository.impl.UserRepositoryImpl;

public interface UserService {
    UserRepositoryImpl userRepo=UserRepositoryImpl.getInstance();

    boolean register(User user);
    User login(String email,String password);
    User getUserProfile(String userId);
}
