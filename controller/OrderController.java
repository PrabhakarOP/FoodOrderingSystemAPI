package controller;

import model.Order;
import service.impl.OrderServiceImpl;

import java.util.ArrayList;

public class OrderController {
    OrderServiceImpl orderService= OrderServiceImpl.getInstance();

    boolean placeOrder(Order order){
        return orderService.placeOrder(order);
    }
    ArrayList<Order> getOrdersByCustomerId(String customerId){
        return orderService.getOrdersByCustomerId(customerId);
    }
    ArrayList<Order> getOrdersByRestaurantId(String restaurantId){
        return orderService.getOrdersByRestaurantId(restaurantId);
    }
    boolean updateOrderStatus(String orderId,String status){
        return orderService.updateOrderStatus(orderId,status);
    }
}
