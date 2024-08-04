package prabhakar.myprojects.food_ordering_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prabhakar.myprojects.food_ordering_system.model.User;
import prabhakar.myprojects.food_ordering_system.model.request.userRequests.UserRegisterRequest;
import prabhakar.myprojects.food_ordering_system.service.UserService;

@RestController
@RequestMapping("/user/v1/api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public boolean register(@RequestBody UserRegisterRequest userRegisterRequest){
        return userService.register(userRegisterRequest);
    }
    @GetMapping("/login")
    public boolean login(@RequestParam String email,@RequestParam String password){
        return userService.login(email,password);
    }

    @GetMapping("/getUserProfile")
    public User getUserProfile(@RequestParam String userId){
        return userService.getUserProfile(userId);
    }
}
