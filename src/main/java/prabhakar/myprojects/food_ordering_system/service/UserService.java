package prabhakar.myprojects.food_ordering_system.service;

import org.springframework.stereotype.Service;
import prabhakar.myprojects.food_ordering_system.model.User;
import prabhakar.myprojects.food_ordering_system.model.request.userRequests.UserRegisterRequest;

@Service
public interface UserService {

    boolean register(UserRegisterRequest userRegisterRequest);
    boolean login(String email,String password);
    User getUserProfile(String userId);
}

