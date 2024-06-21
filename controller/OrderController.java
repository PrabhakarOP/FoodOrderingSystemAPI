package controller;

import model.Order;
import service.impl.OrderServiceImpl;

import java.util.ArrayList;

public class OrderController {
    OrderServiceImpl orderService= OrderServiceImpl.getInstance();

    //singleton design pattern
    private static OrderController instance;

    public static synchronized OrderController getInstance(){
        if(instance==null)
            instance=new OrderController();
        return instance;
    }

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
