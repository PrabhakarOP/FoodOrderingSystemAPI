package repository;

import model.Restaurant;

import java.util.ArrayList;

public interface RestaurantRepository {
    ArrayList<Restaurant> restaurantList=new ArrayList<>();

    void save(Restaurant restaurant);
    ArrayList<Restaurant> findByOwnerId(String ownerId);

    //***********extra functions not mentioned in assignment***********

    Restaurant findByRestaurantId(String restaurantId);
    void deleteRestaurant(Restaurant restaurant);
    void updateRestaurant(String restaurantId,Restaurant restaurant);
    ArrayList<Restaurant> getAllRestaurants();
    Restaurant findByPhoneNumber(String phoneNumber);
}
