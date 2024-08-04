package prabhakar.myprojects.food_ordering_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import prabhakar.myprojects.food_ordering_system.model.User;
import prabhakar.myprojects.food_ordering_system.model.request.userRequests.UserRegisterRequest;
import prabhakar.myprojects.food_ordering_system.repository.UserRepository;
import prabhakar.myprojects.food_ordering_system.service.UserService;

import java.util.NoSuchElementException;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean register(UserRegisterRequest userRegisterRequest) {
        if(userRepository.findByEmail(userRegisterRequest.getEmail())!=null)
            return false;

        User user=new User();
        user.setUsername(userRegisterRequest.getUserName());
        user.setPassword(userRegisterRequest.getPassword());
        user.setEmail(userRegisterRequest.getEmail());
        user.setRole(userRegisterRequest.getRole());

        return userRepository.save(user)!=null;
    }

    @Override
    public boolean login(String email, String password) {
        User user=userRepository.findByEmail(email);
        if(user==null)
            return false;
        else
            return password.equals(user.getPassword());
    }

    @Override
    public User getUserProfile(String userId) {
        try {
            return userRepository.findById(Integer.valueOf(userId)).get();
        }
        catch (NoSuchElementException ex){
            return null;
        }
    }
}
