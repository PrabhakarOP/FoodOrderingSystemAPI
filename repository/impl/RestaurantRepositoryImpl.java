package repository.impl;

import model.Restaurant;
import repository.RestaurantRepository;

public class RestaurantRepositoryImpl implements RestaurantRepository {
    public void save(Restaurant restaurant) {
        restaurantList.add(restaurant);
    }

    public Restaurant findByOwnerId(String ownerId) {
        for(Restaurant restaurant: restaurantList){
            if(restaurant.getOwnerId().equals(ownerId))
                return restaurant;
        }
        return null;
    }
}
