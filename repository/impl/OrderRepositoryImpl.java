package repository.impl;

import model.Order;

import java.util.ArrayList;

public class OrderRepositoryImpl implements repository.OrderRepository {
    //singleton design pattern
    private static OrderRepositoryImpl instance;

    public static synchronized OrderRepositoryImpl getInstance(){
        if(instance==null)
            instance=new OrderRepositoryImpl();
        return instance;
    }

    public void save(Order order) {
        orderList.add(order);
    }

    public ArrayList<Order> findByCustomerId(String customerId) {
        ArrayList<Order> theCustomerOrders=new ArrayList<>();

        for(Order x: orderList){
            if(x.getCustomerId().equals(customerId))
                theCustomerOrders.add(x);
        }

        if(theCustomerOrders.isEmpty())
            return null;
        return theCustomerOrders;
    }

    public ArrayList<Order> findByRestaurantId(String restaurantId) {
        ArrayList<Order> theRestaurantOrders=new ArrayList<>();

        for(Order x: orderList){
            if(x.getRestaurantId().equals(restaurantId))
                theRestaurantOrders.add(x);
        }
        if(theRestaurantOrders.isEmpty())
            return null;
        return theRestaurantOrders ;
    }

    public Order findByOrderId(String orderId) {
        for(Order x: orderList){
            if(x.getId().equals(orderId))
                return x;
        }
        return null;
    }
}
