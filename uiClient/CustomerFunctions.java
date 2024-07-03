package uiClient;

import controller.FoodItemController;
import controller.OrderController;
import controller.RestaurantController;
import helper.Helper;
import model.FoodItem;
import model.Order;
import model.Restaurant;
import model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerFunctions {
    static OrderController orderController = OrderController.getInstance();
    static RestaurantController restaurantController = RestaurantController.getInstance();
    static FoodItemController foodItemController = FoodItemController.getInstance();
    static Scanner sc = new Scanner(System.in);

    static void viewCustomerProfile(User user) {
        System.out.println("\n********************");
        System.out.println("* Customer Profile *");
        System.out.println("********************\n");

        System.out.printf("%-15s %s\n", "Id:", user.getId());
        System.out.printf("%-15s %s\n", "Name:", user.getUsername());
        System.out.printf("%-15s %s\n", "Email:", user.getEmail());
        //calculate total orders
        int totalOrders;
        if (orderController.getOrdersByCustomerId(user.getId()).isEmpty()) totalOrders = 0;
        else totalOrders = orderController.getOrdersByCustomerId(user.getId()).size();
        System.out.printf("%-15s %d\n", "Total Orders:", totalOrders);

        System.out.print("Press enter to go back: ");
        sc.nextLine();
    } //completed

    static void placeOrder(User user) {
        System.out.println("\n***************");
        System.out.println("* Place Order *");
        System.out.println("***************\n");

        //select Restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getAllRestaurants());
        if (selectedRestaurant == null) return;

        //select food Items
        ArrayList<FoodItem> selectedRestaurantAvailableFoodItems = foodItemController.getAvailableFoodItemsByRestaurantId(selectedRestaurant.getId());
        if (selectedRestaurantAvailableFoodItems.isEmpty()) {
            System.out.print("This restaurant has no food Item available ...... Redirecting to customer's homepage...");
            Helper.runTimer(3);
            return;
        }
        ArrayList<FoodItem> selectedFoodItems = new ArrayList<>();
        String ch = "y";
        do {
            FoodItem selectedFoodItem = SelectFromListFunctions.selectFoodItemFromListOf(selectedRestaurantAvailableFoodItems);
            if (selectedFoodItem == null) return;
            selectedFoodItems.add(selectedFoodItem);
            System.out.print("Do you want to add more(y/n): ");
            ch = sc.nextLine();
        } while (ch.equalsIgnoreCase("y"));

        //place order
        float orderValue = calculateOrderValue(selectedFoodItems);
        System.out.println("Your order value: Rs-" + orderValue);
        System.out.print("Confirm to place order (y/n): ");
        String in = sc.nextLine();
        if (in.equalsIgnoreCase("y")) {
            Order order = new Order(user.getId(), selectedRestaurant.getId(), selectedFoodItems);
            if (orderController.placeOrder(order)) System.out.println("** Your Order is placed **");
            else System.out.println("!!! something went wrong try again later !!!");

            System.out.print("Redirecting to customer's homepage...");
            Helper.runTimer(3);
        } else {
            System.out.print("Cancelling your order. please wait");
            Helper.runTimer(3);
        }
    } //completed

    static void viewOrderHistory(User user) {
        System.out.println("\n\n**********************");
        System.out.println("* Your Order History *");
        System.out.println("**********************\n");

        ShowListFunctions.showOrdersFromListOf(orderController.getOrdersByCustomerId(user.getId()));
        System.out.print("Press Enter to go back: ");
        sc.nextLine();
    }//complete

    static float calculateOrderValue(ArrayList<FoodItem> selectedFoodItems) {
        float orderValue = 0;
        for (FoodItem f : selectedFoodItems) {
            orderValue += f.getPrice();
        }
        return orderValue;
    }
}
