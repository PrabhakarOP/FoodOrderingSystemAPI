package prabhakar.myprojects.food_ordering_system.service;

import org.springframework.stereotype.Service;
import prabhakar.myprojects.food_ordering_system.model.Restaurant;
import prabhakar.myprojects.food_ordering_system.model.request.RestaurantRequests.CreateRestaurantRequest;
import prabhakar.myprojects.food_ordering_system.model.request.RestaurantRequests.UpdateRestaurantRequest;

import java.util.List;

@Service
public interface RestaurantService {
    boolean createRestaurant(CreateRestaurantRequest createRestaurantRequest);
    boolean updateRestaurant(String restaurantId, UpdateRestaurantRequest updateRestaurantRequest);
    boolean deleteRestaurant(String restaurantId);
    List<Restaurant> getRestaurantsByOwnerId(String ownerId);
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantByRestaurantId(String restaurantId);
    Restaurant getRestaurantByPhoneNumber(String phoneNumber);
}
