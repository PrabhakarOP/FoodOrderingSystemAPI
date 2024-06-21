package service;

import model.Restaurant;
import repository.impl.RestaurantRepositoryImpl;

public interface RestaurantService {
    RestaurantRepositoryImpl restaurantRepo=RestaurantRepositoryImpl.getInstance();

    boolean createRestaurant(Restaurant restaurant);
    boolean updateRestaurant(String restaurantId,Restaurant restaurant);
    boolean deleteRestaurant(String restaurantId);
    Restaurant getRestaurantsByOwnerId(String ownerId);
}
