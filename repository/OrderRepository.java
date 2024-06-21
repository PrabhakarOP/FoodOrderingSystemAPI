package repository;

import model.Order;

import java.util.ArrayList;

public interface OrderRepository {
    ArrayList<Order> orderList=new ArrayList<>();

    void save(Order order);
    ArrayList<Order> findByCustomerId(String customerId);
    ArrayList<Order> findByRestaurantId(String restaurantId);

    //***********extra functions not mentioned in assignment***********

    Order findByOrderId(String orderId);
}
