package prabhakar.myprojects.food_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prabhakar.myprojects.food_ordering_system.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByCustomerId(Integer customerId);
    List<Order> findByRestaurantId(Integer restaurantId);
    List<Order> findByRestaurantIdAndStatus(Integer restaurantId,String status);
}
