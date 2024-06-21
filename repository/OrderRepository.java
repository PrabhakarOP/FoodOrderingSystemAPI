package repository;

import model.Order;

import java.util.ArrayList;

public interface OrderRepository {
    ArrayList<Order> orderList=new ArrayList<>();

    void save(Order order);
    Order findByCustomerId(String customerId);
    Order findByRestaurantId(String restaurantId);
}
