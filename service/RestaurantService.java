package service;

import model.Restaurant;
import repository.impl.RestaurantRepositoryImpl;

import java.util.ArrayList;

public interface RestaurantService {
    RestaurantRepositoryImpl restaurantRepo=RestaurantRepositoryImpl.getInstance();

    boolean createRestaurant(Restaurant restaurant);
    boolean updateRestaurant(String restaurantId,Restaurant restaurant);
    boolean deleteRestaurant(String restaurantId);
    ArrayList<Restaurant> getRestaurantsByOwnerId(String ownerId);
    ArrayList<Restaurant> getAllRestaurants();
    Restaurant getRestaurantByRestaurantId(String restaurantId);
}
