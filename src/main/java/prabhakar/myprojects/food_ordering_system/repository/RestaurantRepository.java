package prabhakar.myprojects.food_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prabhakar.myprojects.food_ordering_system.model.Restaurant;

import java.util.List;
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    Restaurant save(Restaurant restaurant);
    List<Restaurant> findByOwnerId(Integer ownerId);
    List<Restaurant> findAll();
    Restaurant findByPhoneNumber(String phone);
}
