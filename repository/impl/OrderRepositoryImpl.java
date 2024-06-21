package repository.impl;

import model.Order;

public class OrderRepositoryImpl implements repository.OrderRepository {
    public void save(Order order) {
        orderList.add(order);
    }

    public Order findByCustomerId(String customerId) {
        for(Order order: orderList){
            if(order.getRataurantId().equals(customerId))
                return order;
        }
        return null;
    }

    public Order findByRestaurantId(String restaurantId) {
        for(Order order: orderList){
            if(order.getRataurantId().equals(restaurantId))
                return order;
        }
        return null;
    }
}
