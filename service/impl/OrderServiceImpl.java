package service.impl;

import model.Order;
import service.OrderService;

import java.util.ArrayList;

public class OrderServiceImpl implements OrderService {

    public boolean placeOrder(Order order) {
        orderRepo.save(order);
        return true;
    }


    public ArrayList<Order> getOrdersByCustomerId(String customerId) {
        ArrayList<Order> theCustomerOrders=orderRepo.findByCustomerId(customerId);
        if(theCustomerOrders.isEmpty())
            return null;
        return theCustomerOrders;
    }


    public ArrayList<Order> getOrdersByRestaurantId(String restaurantId) {
        ArrayList<Order> theRestaurantOrders=orderRepo.findByRestaurantId(restaurantId);
        if(theRestaurantOrders.isEmpty())
            return null;
        return theRestaurantOrders;
    }


    public boolean updateOrderStatus(String orderId, String status) {
        Order order=orderRepo.findByOrderId(orderId);
        if(order==null)
            return false;
        order.setStatus(status);
        return true;
    }
}
