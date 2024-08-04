package prabhakar.myprojects.food_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prabhakar.myprojects.food_ordering_system.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    
    User save(User user);

    User findByUsername(String username);
    User findByEmail(String email);

}
