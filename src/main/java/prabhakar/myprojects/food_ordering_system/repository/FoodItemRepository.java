package prabhakar.myprojects.food_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prabhakar.myprojects.food_ordering_system.model.FoodItem;
import prabhakar.myprojects.food_ordering_system.model.Restaurant;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {
    FoodItem save(FoodItem foodItem);


 //   List<FoodItem> findByRestaurantId(Integer restaurantId);
    List<FoodItem> findByRestaurant(Restaurant restaurant);
    List<FoodItem> findByRestaurantIdAndAvailability(Integer restaurantId,boolean availability);
    void delete(FoodItem foodItem);
}
