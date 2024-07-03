package uiClient;

import controller.RestaurantController;
import model.FoodItem;
import model.Order;
import model.Restaurant;

import java.util.ArrayList;

public class ShowListFunctions {
    static RestaurantController restaurantController=RestaurantController.getInstance();

    static void showOrdersFromListOf(ArrayList<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("!! NO Orders found !!");
            return;
        }
        int c = 1;
        for (Order order : orders) {
            System.out.println();
            System.out.println("Order no: " + c);
            c++;
            System.out.println("*******************************************************************");
            System.out.println("* Order Id: " + order.getId() + "                Status: " + order.getStatus() + "               *");
            System.out.printf("* Restaurant Name: %-46s *\n", restaurantController.getRestaurantByRestaurantId(order.getRestaurantId()).getName());
            System.out.println("*                                                                 *");
            System.out.println("*                        ** Food Items **                         *");
            System.out.println("*                                                                 *");
            System.out.printf("* %-15s %6s    %-37s *\n", "Name", "Price", "Description");
            int fc = 1;
            for (FoodItem foodItem : order.getFoodItems()) {
                System.out.printf("* %d. %-15s %-6s %-37s *\n", fc, foodItem.getName(), foodItem.getPrice(), foodItem.getDescription());
                fc++;
            }
            System.out.println("*                                                                 *");
            System.out.printf("* Total Order Value = Rs %-40f *\n", order.getTotalPrice());
            System.out.println("*******************************************************************");
        }
        System.out.println("Total Orders: " + (c - 1));
    }

    static void showRestaurantsFromListOf(ArrayList<Restaurant> restaurants) {
        if (restaurants.isEmpty()) {
            System.out.println("!!! NO Restaurants Found !!!");
            return;
        }

        System.out.printf("%-29s %12s    Address\n", "   Name", "Phone Number");
        int c = 1;
        for (Restaurant restaurant : restaurants) {
            System.out.printf("%-3d. %-25s %-12s    %s\n", c, restaurant.getName(), restaurant.getPhone(), restaurant.getAddress());
            c++;
        }
        System.out.println("\nTotal Restaurants: " + (c - 1));
    }
}
