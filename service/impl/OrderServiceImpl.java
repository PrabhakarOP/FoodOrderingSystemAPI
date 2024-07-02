package service.impl;

import helper.Message;
import model.Order;
import service.OrderService;

import java.util.ArrayList;

public class OrderServiceImpl implements OrderService {
    //singleton design pattern
    private static OrderServiceImpl instance;

    public static synchronized OrderServiceImpl getInstance(){
        if(instance==null)
            instance=new OrderServiceImpl();
        return instance;
    }

    public boolean placeOrder(Order order) {
        orderRepo.save(order);
        return true;
    }


    public ArrayList<Order> getOrdersByCustomerId(String customerId) {
        ArrayList<Order> theCustomerOrders=orderRepo.findByCustomerId(customerId);
        return theCustomerOrders;
    }


    public ArrayList<Order> getOrdersByRestaurantId(String restaurantId) {
        ArrayList<Order> theRestaurantOrders=orderRepo.findByRestaurantId(restaurantId);
        return theRestaurantOrders;
    }


    public ArrayList<Order> getPendingOrdersByRestaurantId(String restaurantId) {
        ArrayList<Order> allOrders=getOrdersByRestaurantId(restaurantId);
        ArrayList<Order> pendingOrders=new ArrayList<>();
        for(Order order: allOrders){
            if(order.getStatus().equalsIgnoreCase("Pending"))
                pendingOrders.add(order);
        }
        return pendingOrders;
    }


    public ArrayList<Order> getInProgressOrdersByRestaurantId(String restaurantId) {
        ArrayList<Order> allOrders=getOrdersByRestaurantId(restaurantId);
        ArrayList<Order> inProgressOrders=new ArrayList<>();

        for(Order order: allOrders){
            if(order.getStatus().equalsIgnoreCase("In_Progress"))
                inProgressOrders.add(order);
        }
        return inProgressOrders;
    }


    public ArrayList<Order> getCompletedOrdersByRestaurantId(String restaurantId) {
        ArrayList<Order> allOrders=getOrdersByRestaurantId(restaurantId);
        ArrayList<Order> completedOrders=new ArrayList<>();

        for(Order order: allOrders){
            if(order.getStatus().equalsIgnoreCase("Completed"))
                completedOrders.add(order);
        }
        return completedOrders;
    }

    public boolean updateOrderStatus(String orderId, String status) {
        Order order=orderRepo.findByOrderId(orderId);
        if(order==null){
            Message.message="No such order found";
            return false;
        }
        order.setStatus(status);
        return true;
    }
}
