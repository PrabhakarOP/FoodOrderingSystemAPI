package repository;

import model.Restaurant;

import java.util.ArrayList;

public interface RestaurantRepository {
    ArrayList<Restaurant> restaurantList=new ArrayList<>();

    void save(Restaurant restaurant);
    Restaurant findByOwnerId(String ownerId);

    //***********extra functions not mentioned in assignment***********

    Restaurant findByRestaurantId(String restaurantId);
    void deleteRestaurant(Restaurant restaurant);
}
