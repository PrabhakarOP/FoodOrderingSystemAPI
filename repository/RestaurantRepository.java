package repository;

import model.Restaurant;

import java.util.ArrayList;

public interface RestaurantRepository {
    ArrayList<Restaurant> restaurantList=new ArrayList<>();

    void save(Restaurant restaurant);
    Restaurant findByOwnerId(String ownerId);
}
