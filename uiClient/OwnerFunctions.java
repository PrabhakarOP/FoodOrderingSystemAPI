package uiClient;

import controller.FoodItemController;
import controller.OrderController;
import controller.RestaurantController;
import helper.Helper;
import helper.Message;
import model.FoodItem;
import model.Order;
import model.Restaurant;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OwnerFunctions {
    static RestaurantController restaurantController = RestaurantController.getInstance();
    static FoodItemController foodItemController = FoodItemController.getInstance();
    static OrderController orderController = OrderController.getInstance();

    static Scanner sc = new Scanner(System.in);

    static void viewOwnerProfile(User user) {
        System.out.println("\n*****************");
        System.out.println("* Owner Profile *");
        System.out.println("*****************\n");

        System.out.printf("%-15s %s\n", "Id:", user.getId());
        System.out.printf("%-15s %s\n", "Name:", user.getUsername());
        System.out.printf("%-15s %s\n", "Email:", user.getEmail());
        System.out.println();
        System.out.printf("%15s** Your Restaurants **\n\n", " ");

        ArrayList<Restaurant> ownerRestaurants = restaurantController.getRestaurantsByOwnerId(user.getId());
        ShowListFunctions.showRestaurantsFromListOf(ownerRestaurants);
        System.out.print("Press enter to go back: ");
        sc.nextLine();
    }

    //********* Restaurant related functions ***************

    static void createRestaurant(User user) {
        System.out.println("\n\n***********************");
        System.out.println("* Create A Restaurant *");
        System.out.println("***********************\n");
        //Input name
        System.out.print("Enter Restaurant name: ");
        String name = InputFunctions.inputName();
        if (name.equalsIgnoreCase("q")) return;

        //Input address
        System.out.print("Enter Restaurant Address: ");
        String address = InputFunctions.inputAddress();
        if (address.equalsIgnoreCase("q")) return;

        //Input Phone Number
        System.out.print("Enter Restaurant Phone Number: ");
        String phoneNumber = InputFunctions.inputPhoneNumber();
        if (phoneNumber.equalsIgnoreCase("q")) return;

        //Create Restaurant
        Restaurant newRestaurant = new Restaurant(user.getId(), name, address, phoneNumber);
        if (restaurantController.createRestaurant(newRestaurant)) System.out.println("   ** Restaurant Created **   ");
        else System.out.println(Message.message + "!!! Failed to Create Restaurant , Try again !!!");
        System.out.print("press enter to continue: ");
        sc.nextLine();
    }

    static void updateRestaurant(User user) {
        System.out.println("\n\n**************************");
        System.out.println("* Update Your Restaurant *");
        System.out.println("**************************\n");

        //select restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;
        Restaurant updatedRestaurant = new Restaurant(selectedRestaurant);     //making clone of selected Restaurant

        //choose field to be updated
        int opt = 0;
        while (true) {
            System.out.println("Choose What You Want to Update: ");
            System.out.println("1. Restaurant Name");
            System.out.println("2. Restaurant Address");
            System.out.println("3. Restaurant PhoneNumber");
            System.out.println("4. Exit");

            System.out.print("Enter your Option: ");
            opt = 0;
            try {
                opt = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }
            sc.nextLine();                //to remove new line character

            int f = 0;
            switch (opt) {
                case 1:
                    //Enter new name
                    System.out.print("Enter new name: ");
                    String newName = InputFunctions.inputName();
                    if (newName.equalsIgnoreCase("q")) return;

                    //create updated restaurant
                    updatedRestaurant.setName(newName);
                    break;
                case 2:
                    //Enter new Address
                    System.out.print("Enter new restaurant address: ");
                    String newAddress = InputFunctions.inputAddress();
                    if (newAddress.equalsIgnoreCase("q")) return;

                    //create updated restaurant
                    updatedRestaurant.setAddress(newAddress);
                    break;
                case 3:
                    //Input New Phone Number
                    System.out.print("Enter Restaurant's New Phone Number: ");
                    String newPhoneNumber = InputFunctions.inputPhoneNumber();
                    if (newPhoneNumber.equalsIgnoreCase("q")) return;
                    //create updated restaurant
                    updatedRestaurant.setPhone(newPhoneNumber);
                    break;
                case 4:
                    return;
                default:
                    f = 1;
                    System.out.print("!!!Invalid Input!!!");
                    System.out.print(" please wait");
                    Helper.runTimer(2);
            }
            if (f == 0) break;
        }

        //updateRestaurant
        if (restaurantController.updateRestaurant(selectedRestaurant.getId(), updatedRestaurant))
            System.out.println("***** Restaurant Updated Successfully *****");
        else System.out.println(Message.message + " Failed to update Restaurant");

        System.out.print("Redirecting to owner's page....");
        Helper.runTimer(3);
    }

    static void deleteRestaurant(User user) {
        System.out.println("\n\n**************************");
        System.out.println("* Delete Your Restaurant *");
        System.out.println("**************************\n");

        //select restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;
        //delete the Restaurant
        if (restaurantController.deleteRestaurant(selectedRestaurant.getId()))
            System.out.println("**** Restaurant Deleted ****");
        else System.out.println(Message.message + " Failed to delete the restaurant");

        System.out.print("Redirecting to owner's homepage ...");
        Helper.runTimer(3);
    }

    //********* FoodItem related functions **********

    static void addFoodItems(User user) {
        System.out.println("\n\n*****************");
        System.out.println("* Add Food Item *");
        System.out.println("*****************\n");

        //select restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //enter new FoodItem name
        System.out.print("Enter new FoodItem name: ");
        String newFoodItemName = InputFunctions.inputName();
        if (newFoodItemName.equalsIgnoreCase("q")) return;
        //enter description
        System.out.print("Enter description: ");
        String description = InputFunctions.inputDescription();
        if (description.equalsIgnoreCase("q"))           //abort the process
            return;

        //enter price
        float price = InputFunctions.inputPrice();
        if (price == -1) return;
        //create newFoodItem object
        FoodItem newFoodItem = new FoodItem(selectedRestaurant.getId(), newFoodItemName, description, price);
        //Add the food item
        if (foodItemController.addFoodItem(selectedRestaurant.getId(), newFoodItem))
            System.out.println("**** Food Item added ****");
        else System.out.println(Message.message + " Failed to add FoodItem");

        System.out.print("Redirecting to owner's homePage...");
        Helper.runTimer(3);
    }

    static void updateFoodItem(User user) {
        System.out.println("\n\n********************");
        System.out.println("* Update Food Item *");
        System.out.println("********************\n");

        //select restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //select foodItem
        FoodItem selectedFoodItem = SelectFromListFunctions.selectFoodItemFromListOf(selectedRestaurant.getFoodItems());
        if (selectedFoodItem == null) return;
        FoodItem updatedFoodItem = new FoodItem(selectedFoodItem);  //making clone of selected foodItem

        //choose field to be updated
        int opt = 0;
        while (true) {
            System.out.println("Choose What You Want to Update: ");
            System.out.println("1. FoodItem Name");
            System.out.println("2. FoodItem Price");
            System.out.println("3. FoodItem Description");
            System.out.println("4. Exit");

            System.out.print("Enter your Option: ");
            opt = 0;
            try {
                opt = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }
            sc.nextLine();                //to remove new line character

            int f = 0;
            switch (opt) {
                case 1:
                    //Enter new Name
                    System.out.print("Enter new name: ");
                    String newName = InputFunctions.inputName();
                    if (newName.equalsIgnoreCase("q"))           //abort the process
                        return;


                    //create updated foodItem
                    updatedFoodItem.setName(newName);
                    break;
                case 2:
                    //enter new Price
                    float newPrice = InputFunctions.inputPrice();
                    if (newPrice == -1) return;
                    //create updated foodItem
                    updatedFoodItem.setPrice(newPrice);
                    break;
                case 3:
                    //enter new description
                    System.out.print("Enter description: ");
                    String newDescription = InputFunctions.inputDescription();
                    if (newDescription.equalsIgnoreCase("q"))           //abort the process
                        return;

                    //update description
                    updatedFoodItem.setDescription(newDescription);
                    break;
                case 4:
                    return;
                default:
                    f = 1;
                    System.out.print("!! Invalid Input !! .Please wait...");
                    Helper.runTimer(3);
            }
            if (f == 0) break;
        }
        //update foodItem
        if (foodItemController.updateFoodItem(selectedFoodItem.getId(), updatedFoodItem))
            System.out.println("** Food Item Updated Successfully");
        else System.out.println(Message.message + " Failed to update FoodItem");
        System.out.print("Redirecting to owner's homepage..");
        Helper.runTimer(3);
    }

    static void deleteFoodItem(User user) {
        System.out.println("\n\n********************");
        System.out.println("* Delete Food Item *");
        System.out.println("********************\n");

        //select restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //select foodItem
        FoodItem selectedFoodItem = SelectFromListFunctions.selectFoodItemFromListOf(selectedRestaurant.getFoodItems());
        if (selectedFoodItem == null) return;

        //delete foodItem
        if (foodItemController.deleteFoodItem(selectedFoodItem.getId()))
            System.out.println("** Food Item Deleted Successfully **");
        else System.out.println(Message.message + "Failed to delete the Food Item");
        System.out.print("Redirection to owner's HomePage...");
        Helper.runTimer(3);
    }

    static void updateFoodItemAvailability(User user) {
        System.out.println("\n\n***********************");
        System.out.println("* Update Availability *");
        System.out.println("***********************\n");

        //select restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //select foodItem
        FoodItem selectedFoodItem = SelectFromListFunctions.selectFoodItemFromListOf(selectedRestaurant.getFoodItems());
        if (selectedFoodItem == null) return;

        //choose availability
        String choice;
        boolean availability;
        while (true) {
            System.out.print("Is this Food Item available (y/n): ");
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                availability = true;
                break;
            } else if (choice.equalsIgnoreCase("n")) {
                availability = false;
                break;
            } else {
                System.out.println("Incorrect Input, enter Y for yes OR n for no");
            }
        }

        //update availability
        if (foodItemController.updateFoodItemAvailability(selectedFoodItem.getId(), availability))
            System.out.println("Availability updated successfully");
        else System.out.println(Message.message + "Failed to update availability");
        System.out.print("Redirecting to owner's homepage...");
        Helper.runTimer(3);
    }

    //********* Orders Related functions **************

    static void showAllOrders(User user) {
        System.out.println("\n\n******************");
        System.out.println("* Orders Section *");
        System.out.println("******************\n");

        //select restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //show orders
        System.out.println("\n\n******************************");
        System.out.println("* Restaurant's Order History *");
        System.out.println("******************************\n");

        ShowListFunctions.showOrdersFromListOf(orderController.getOrdersByRestaurantId(selectedRestaurant.getId()));
        System.out.print("Press enter to go back: ");
        sc.nextLine();
    }

    static void showPendingOrders(User user) {
        System.out.println("\n\n**************************");
        System.out.println("* Pending Orders Section *");
        System.out.println("**************************\n");
        //select restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //show orders
        System.out.println("\n\n******************");
        System.out.println("* Pending Orders *");
        System.out.println("******************\n");

        ShowListFunctions.showOrdersFromListOf(orderController.getPendingOrdersByRestaurantId(selectedRestaurant.getId()));
        System.out.print("Press enter to go back: ");
        sc.nextLine();
    }

    static void showInProgressOrders(User user) {
        System.out.println("\n\n******************************");
        System.out.println("* In_Progress Orders Section *");
        System.out.println("******************************\n");

        //select restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //show orders
        System.out.println("\n\n**********************");
        System.out.println("* In_Progress Orders *");
        System.out.println("**********************\n");

        ShowListFunctions.showOrdersFromListOf(orderController.getInProgressOrdersByRestaurantId(selectedRestaurant.getId()));
        System.out.print("Press enter to go back: ");
        sc.nextLine();
    }

    static void showCompletedOrders(User user) {
        System.out.println("\n\n****************************");
        System.out.println("* Completed Orders Section *");
        System.out.println("****************************\n");

        //select restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //show orders
        System.out.println("\n\n********************");
        System.out.println("* Completed Orders *");
        System.out.println("********************\n");

        ShowListFunctions.showOrdersFromListOf(orderController.getCompletedOrdersByRestaurantId(selectedRestaurant.getId()));
        System.out.print("Press enter to go back: ");
        sc.nextLine();
    }

    static void updateOrderStatus(User user) {
        System.out.println("\n\n********************************");
        System.out.println("* Update Orders Status Section *");
        System.out.println("********************************\n");

        //select restaurant
        Restaurant selectedRestaurant = SelectFromListFunctions.selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //choose which order to update
        System.out.println("Update status of: ");
        System.out.println("1. Pending Orders");
        System.out.println("2. In_Progress Orders");

        int opt1 = 0;
        while (true) {
            System.out.print("Choose your option: ");
            try {
                opt1 = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }
            sc.nextLine();                                  //remove new line character

            if (opt1 == -1)                                  // aborts the process
                return;
            else if (opt1 < 1 || opt1 > 2) {
                System.out.print("!!!! Invalid option !!!!");
                System.out.print("Enter  q to abort , anything to try again: ");
                String in = sc.nextLine();
                if (in.equalsIgnoreCase("q"))            //aborts the process
                    return;
            } else break;
        }

        Order selectedOrder = null;

        if (opt1 == 1) {
            //select a pending order
            System.out.println("\n\n******************");
            System.out.println("* Pending Orders *");
            System.out.println("******************\n");

            selectedOrder = SelectFromListFunctions.selectOrderFromListOf(orderController.getPendingOrdersByRestaurantId(selectedRestaurant.getId()));
            if (selectedOrder == null) return;
        }

        if (opt1 == 2) {
            //select an in_progress order
            System.out.println("\n\n**********************");
            System.out.println("* In_Progress Orders *");
            System.out.println("**********************\n");

            selectedOrder = SelectFromListFunctions.selectOrderFromListOf(orderController.getInProgressOrdersByRestaurantId(selectedRestaurant.getId()));
            if (selectedOrder == null) return;
        }

        //update order status
        String status;
        while (true) {
            System.out.print("Enter status (p for pending / i for in progress / c for completed): ");
            status = sc.nextLine();
            if (status.equalsIgnoreCase("p")) {
                status = "PENDING";
                break;
            } else if (status.equalsIgnoreCase("i")) {
                status = "IN_PROGRESS";
                break;
            } else if (status.equalsIgnoreCase("c")) {
                status = "COMPLETED";
                break;
            } else {
                System.out.print("!! Invalid Entry !!. press enter to try again: ");
                sc.nextLine();
            }
        }

        if (orderController.updateOrderStatus(selectedOrder.getId(), status))
            System.out.println("Order status updated successfully");
        else System.out.println(Message.message + " Failed to update order status");
        System.out.print("Redirecting to homepage...");
        Helper.runTimer(3);
    }
}
