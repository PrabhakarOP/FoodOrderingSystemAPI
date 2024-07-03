package uiClient;

import helper.Helper;
import model.FoodItem;
import model.Order;
import model.Restaurant;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SelectFromListFunctions {
    static Scanner sc = new Scanner(System.in);

    static Restaurant selectRestaurantFromListOf(ArrayList<Restaurant> restaurants) {
        //show restaurants
        if (restaurants.isEmpty()) {
            System.out.print("!!! No restaurants available !!! Going back , Please wait");
            Helper.runTimer(3);
            return null;
        }

        System.out.println("Available Restaurants: ");
        int c = 1;
        System.out.printf("%-18s %s\n", "Restaurant Name", "Food Items");
        for (Restaurant r : restaurants) {
            System.out.printf("%d. %-15s  %d\n", c, r.getName(), r.getFoodItems().size());
            c++;
        }

        //choose restaurant
        int opt = 0;
        while (true) {
            System.out.print("Choose restaurant number: ");
            try {
                opt = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }
            sc.nextLine();                                  //remove new line character

            if (opt == -1) return null;
            else if (opt < 1 || opt > restaurants.size()) {
                System.out.print("!!!! Invalid option !!!!");
                System.out.print("Enter  q to abort , anything to try again: ");
                String in = sc.nextLine();
                if (in.equalsIgnoreCase("q")) return null;
            } else break;
        }
        return restaurants.get(opt - 1);
    }

    static FoodItem selectFoodItemFromListOf(ArrayList<FoodItem> foodItems) {
        //show foodItem list
        if (foodItems.isEmpty()) {
            System.out.print("This restaurant has no food Item available......Redirecting to customer homepage");
            Helper.runTimer(3);
            return null;
        }
        System.out.println("***** Available FoodItems ***** \n");
        System.out.printf("%-15s %6s   %10s    %s\n", "     Name", "Price", "Available", "Description");
        int c = 1;
        for (FoodItem foodItem : foodItems) {
            System.out.printf("%d. %-15s %-6s   %-10s %s\n", c, foodItem.getName(), foodItem.getPrice(), foodItem.isAvailabel() ? "yes" : "no", foodItem.getDescription());
            c++;
        }

        //choose foodItem
        int opt = 0;
        while (true) {
            System.out.print("Choose foodItem number: ");
            try {
                opt = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }
            sc.nextLine();                                  //remove new line character

            if (opt == -1) return null;
            else if (opt < 1 || opt > foodItems.size()) {
                System.out.print("!!!! Invalid option !!!!");
                System.out.print("Enter  q to abort , anything to try again: ");
                String in = sc.nextLine();
                if (in.equalsIgnoreCase("q")) return null;

            } else break;
        }
        return foodItems.get(opt - 1);
    }

    static Order selectOrderFromListOf(ArrayList<Order> orders) {
        //show orders
        if (orders.isEmpty()) {
            System.out.println("!! NO Orders found !!");
            System.out.print("Press enter to go back: ");
            sc.nextLine();
            return null;
        }
        ShowListFunctions.showOrdersFromListOf(orders);

        //select order
        int opt = 0;
        while (true) {
            System.out.print("Choose order number : ");
            try {
                opt = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }
            sc.nextLine();                                  //remove new line character

            if (opt == -1)                                  // aborts the process
                return null;
            else if (opt < 1 || opt > orders.size()) {
                System.out.print("!!!! Invalid option !!!!");
                System.out.print("Enter  q to abort , anything to try again: ");
                String in = sc.nextLine();
                if (in.equalsIgnoreCase("q"))            //aborts the process
                    return null;
            } else break;
        }
        return orders.get(opt - 1);
    }
}
