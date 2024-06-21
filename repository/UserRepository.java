package repository;

import model.User;

import java.util.ArrayList;

public interface UserRepository {
    ArrayList<User> userList=new ArrayList<>();

    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUserId(String userId);
}
