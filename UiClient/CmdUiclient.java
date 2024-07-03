package UiClient;

import controller.FoodItemController;
import controller.OrderController;
import controller.RestaurantController;
import controller.UserController;
import helper.Helper;
import helper.Message;
import model.FoodItem;
import model.Order;
import model.Restaurant;
import model.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CmdUiclient {
    static UserController userController = UserController.getInstance();
    static RestaurantController restaurantController = RestaurantController.getInstance();
    static FoodItemController foodItemController = FoodItemController.getInstance();
    static OrderController orderController = OrderController.getInstance();

    static Scanner sc = new Scanner(System.in);

    //populate some data
    static {
        //populate customer
        userController.register(new User("Prince Prabhakar", "1234", "playhorn.pp@gmail.com", "Customer"));
        userController.register(new User("Alok Kumar", "1234", "yuviyuvrajy9@gmail.com", "Customer"));
        userController.register(new User("Sumit kumar", "1234", "sumitkumar@gmail.com", "Customer"));

        //populate Owner
        userController.register(new User("Raushan kumar", "1234", "raushan22july@gmail.com", "Owner"));
        userController.register(new User("Suraj kumar", "1234", "suraj77@gmail.com", "Owner"));

        //populate restaurants
        User ownerRaushan = userController.login("raushan22july@gmail.com", "1234");
        User ownerSuraj = userController.login("suraj77@gmail.com", "1234");
        restaurantController.createRestaurant(new Restaurant(ownerRaushan.getId(), "Asli Momo", "Raja bazar", "6205221206"));
        restaurantController.createRestaurant(new Restaurant(ownerSuraj.getId(), "Pet Puja", "Staion road", "7717772453"));

        //populate foodItems
        Restaurant raushanRestaurant = restaurantController.getRestaurantByPhoneNumber("6205221206");
        FoodItem vegMomo = new FoodItem(raushanRestaurant.getId(), "Veg Momo", "veggies filled dumplings", 40);
        FoodItem springRoll = new FoodItem(raushanRestaurant.getId(), "Spring Roll", "Veggies filled crunchy rolls", 30);
        FoodItem paneerMomo = new FoodItem(raushanRestaurant.getId(), "Paneer Momo", "Paneer filled dumplings", 50);
        FoodItem chickenMomo = new FoodItem(raushanRestaurant.getId(), "Chicken Momo", "Granulated chicken filled dumplings", 60);
        //add the foodItems to the restaurant
        foodItemController.addFoodItem(raushanRestaurant.getId(), vegMomo);
        foodItemController.addFoodItem(raushanRestaurant.getId(), springRoll);
        foodItemController.addFoodItem(raushanRestaurant.getId(), paneerMomo);
        foodItemController.addFoodItem(raushanRestaurant.getId(), chickenMomo);
    }


    public static void main(String[] args) {


        System.out.println("\n\n**********************************************************");
        System.out.println("*              Welcome To Food Ordering App              *");
        System.out.println("**********************************************************");

        while (true) {
            System.out.println("\n***************");
            System.out.println("*  HOME PAGE  *");
            System.out.println("***************\n");

            System.out.println("Options:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit\n");

            System.out.print("Enter your option: ");
            int opt = 0;

            try {
                opt = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }

            sc.nextLine();                           // To remove new Line character from input stream.

            switch (opt) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option!");
                    System.out.print("press Enter to go to home page : ");
                    sc.nextLine();
            }
        }
    } //completed

    static void register() {


        System.out.println("\n*********************");
        System.out.println("* REGISTRATION PAGE *");
        System.out.println("****************");


        System.out.print("Register as restaurant owner (Y/N): ");
        String opt = sc.nextLine();
        String role;

        if (opt.equalsIgnoreCase("y")) role = "Owner";
        else if (opt.equalsIgnoreCase("n")) role = "Customer";
        else {
            System.out.println("Invalid Input!  redirecting to HOME PAGE--> .");
            return;
        }
        //enter name
        System.out.print("Enter Name: ");
        String name = InputFunctions.inputName();                        //Aborts the current process and back to previous menu.
        if (name.equalsIgnoreCase("q"))
            return;

        //enter email
        System.out.print("Enter Email: ");
        String email = InputFunctions.inputEmail();
        if (email.equalsIgnoreCase("q"))                   //Aborts the current process and back to previous menu.
            return;

        //enter password
        System.out.print("Enter your password: ");
        String password = InputFunctions.inputPassword();
        if (password.equalsIgnoreCase("q"))                  //Aborts the current process and back to previous menu.
            return;

        //Now signUP
        User newUser = new User(name, password, email, role);
        if (userController.register(newUser))
            System.out.println("<--------signed Up successfully.Now you can signIn.------->");
        else System.out.println(Message.message + " ! SignUp failed !  Try again....");

        System.out.print("press Enter to go to home page : ");
        sc.nextLine();

    }  //completed

    static void login() {


        System.out.println("\n****************");
        System.out.println("*  LOGIN PAGE  *");
        System.out.println("****************\n");

        //email
        System.out.print("Enter registered email: ");
        String email=InputFunctions.inputEmail();
        if(email.equalsIgnoreCase("q"))
            return;                                     //aborts the process

        //password
        System.out.print("Enter your password: ");
        String password=InputFunctions.inputPassword();
        if(password.equalsIgnoreCase("q"))
            return;
        //now login
        User user = userController.login(email, password);

        if (user == null) {
            System.out.print(Message.message + " Login failed....Redirecting to homepage");
            Helper.runTimer(5);
            return;
        }

        if (user.getRole().equalsIgnoreCase("customer")) customerHomePage(user);
        else ownerHomePage(user);
    }

    static void customerHomePage(User user) {
        while (true) {
            System.out.println("\n\n**********************");
            System.out.println("* Customer Home Page *");
            System.out.println("**********************\n");

            System.out.println("Options:");
            System.out.println("1. View My Profile");
            System.out.println("2. Place Order");
            System.out.println("3. View My Order History");
            System.out.println("4. Logout");

            System.out.print("Enter your Option: ");
            int opt = 0;
            try {
                opt = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }
            sc.nextLine();                //to remove new line character

            switch (opt) {
                case 1:
                    viewCustomerProfile(user);
                    break;
                case 2:
                    placeOrder(user);
                    break;
                case 3:
                    viewOrderHistory(user);
                    break;
                case 4:
                    return;
                default:
                    System.out.print("!!!Invalid Input!!!");
                    System.out.print(" please wait");
                    Helper.runTimer(5);
            }
        }
    }

    static void ownerHomePage(User user) {
        while (true) {
            System.out.println("\n\n*******************");
            System.out.println("* Owner Home Page *");
            System.out.println("*******************\n");

            System.out.println("Options:");
            System.out.println("1.  View My Profile");
            System.out.println("2.  Create a Restaurant");
            System.out.println("3.  Update a Restaurant");
            System.out.println("4.  Delete a Restaurant");
            System.out.println("5.  Add foodItems to a Restaurant");
            System.out.println("6.  Update foodItems of a Restaurant");
            System.out.println("7.  Delete foodItems of a Restaurant");
            System.out.println("8.  Update foodItem availability");
            System.out.println("9.  View all orders");
            System.out.println("10. View PENDING orders");
            System.out.println("11. View Orders IN_PROGRESS");
            System.out.println("12. View COMPLETED Orders");
            System.out.println("13. Update Order status");
            System.out.println("14. Log Out");
            System.out.print("Enter your Option: ");
            int opt = 0;
            try {
                opt = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }
            sc.nextLine();                //to remove new line character

            switch (opt) {
                case 1:
                    viewOwnerProfile(user);
                    break;
                case 2:
                    createRestaurant(user);
                    break;
                case 3:
                    updateRestaurant(user);
                    break;
                case 4:
                    deleteRestaurant(user);
                    break;
                case 5:
                    addFoodItems(user);
                    break;
                case 6:
                    updateFoodItem(user);
                    break;
                case 7:
                    deleteFoodItem(user);
                    break;
                case 8:
                    updateFoodItemAvailability(user);
                case 9:
                    showAllOrders(user);
                    break;
                case 10:
                    showPendingOrders(user);
                    break;
                case 11:
                    showInProgressOrders(user);
                    break;
                case 12:
                    showCompletedOrders(user);
                    break;
                case 13:
                    updateOrderStatus(user);
                    break;
                case 14:
                    return;
                default:
                    System.out.print("!!!Invalid Input!!!");
                    System.out.print(" please wait");
                    Helper.runTimer(3);
            }
        }
    }

    //************* customer functions ***************
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
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getAllRestaurants());
        if (selectedRestaurant == null) return;

        //select food Items
        ArrayList<FoodItem> selectedFoodItems = new ArrayList<>();
        String ch = "y";
        do {
            FoodItem selectedFoodItem = selectFoodItemFromListOf(selectedRestaurant.getFoodItems());
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

        showOrdersFromListOf(orderController.getOrdersByCustomerId(user.getId()));
        System.out.print("Press Enter to go back: ");
        sc.nextLine();
    }//complete

    //************ owner functions **************
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
        showRestaurantsFromListOf(ownerRestaurants);
        System.out.print("Press enter to go back: ");
        sc.nextLine();
    } //completed

    static void createRestaurant(User user) {
        System.out.println("\n\n***********************");
        System.out.println("* Create A Restaurant *");
        System.out.println("***********************\n");
        //Input name
        System.out.print("Enter Restaurant name: ");
        String name=InputFunctions.inputName();
        if(name.equalsIgnoreCase("q"))
            return;

        //Input address
        System.out.print("Enter Restaurant Address: ");
        String address = InputFunctions.inputAddress();
        if(address.equalsIgnoreCase("q"))
            return;

        //Input Phone Number
        System.out.print("Enter Restaurant Phone Number: ");
        String phoneNumber = InputFunctions.inputPhoneNumber();
        if(phoneNumber.equalsIgnoreCase("q"))
            return;

        //Create Restaurant
        Restaurant newRestaurant = new Restaurant(user.getId(), name, address, phoneNumber);
        if (restaurantController.createRestaurant(newRestaurant)) System.out.println("   ** Restaurant Created **   ");
        else System.out.println(Message.message + "!!! Failed to Create Restaurant , Try again !!!");
        System.out.print("press enter to continue: ");
        sc.nextLine();
    } //completed

    static void updateRestaurant(User user) {
        System.out.println("\n\n**************************");
        System.out.println("* Update Your Restaurant *");
        System.out.println("**************************\n");

        //select restaurant
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
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
                    String newName=InputFunctions.inputName();
                    if(newName.equalsIgnoreCase("q"))
                        return;

                    //create updated restaurant
                    updatedRestaurant.setName(newName);
                    break;
                case 2:
                    //Enter new Address
                    System.out.print("Enter new restaurant address: ");
                    String newAddress=InputFunctions.inputAddress();
                    if(newAddress.equalsIgnoreCase("q"))
                        return;

                    //create updated restaurant
                    updatedRestaurant.setAddress(newAddress);
                    break;
                case 3:
                    //Input New Phone Number
                    System.out.print("Enter Restaurant's New Phone Number: ");
                    String newPhoneNumber = InputFunctions.inputPhoneNumber();
                    if(newPhoneNumber.equalsIgnoreCase("q"))
                        return;
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
    } //completed

    static void deleteRestaurant(User user) {
        System.out.println("\n\n**************************");
        System.out.println("* Delete Your Restaurant *");
        System.out.println("**************************\n");

        //select restaurant
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;
        //delete the Restaurant
        if (restaurantController.deleteRestaurant(selectedRestaurant.getId()))
            System.out.println("**** Restaurant Deleted ****");
        else System.out.println(Message.message + " Failed to delete the restaurant");

        System.out.print("Redirection to owner's homepage ...");
        Helper.runTimer(3);
    } //completed

    static void addFoodItems(User user) {
        System.out.println("\n\n*****************");
        System.out.println("* Add Food Item *");
        System.out.println("*****************\n");

        //select restaurant
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //enter new FoodItem name
        System.out.print("Enter new FoodItem name: ");
        String newFoodItemName=InputFunctions.inputName();
        if(newFoodItemName.equalsIgnoreCase("q"))
            return;
        //enter description
        System.out.print("Enter description: ");
        String description = InputFunctions.inputDescription();
        if (description.equalsIgnoreCase("q"))           //abort the process
            return;

        //enter price
        float price = InputFunctions.inputPrice();
        if(price==-1)
            return;
        //create newFoodItem object
        FoodItem newFoodItem = new FoodItem(selectedRestaurant.getId(), newFoodItemName, description, price);
        //Add the food item
        if (foodItemController.addFoodItem(selectedRestaurant.getId(), newFoodItem))
            System.out.println("**** Food Item added ****");
        else System.out.println(Message.message + " Failed to add FoodItem");

        System.out.print("Redirecting to owner's homePage...");
        Helper.runTimer(3);
    } //completed

    static void updateFoodItem(User user) {
        System.out.println("\n\n********************");
        System.out.println("* Update Food Item *");
        System.out.println("********************\n");

        //select restaurant
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //select foodItem
        FoodItem selectedFoodItem = selectFoodItemFromListOf(selectedRestaurant.getFoodItems());
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
                    if(newPrice==-1)
                        return;
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
    } //completed

    static void deleteFoodItem(User user) {
        System.out.println("\n\n********************");
        System.out.println("* Delete Food Item *");
        System.out.println("********************\n");

        //select restaurant
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //select foodItem
        FoodItem selectedFoodItem = selectFoodItemFromListOf(selectedRestaurant.getFoodItems());
        if (selectedFoodItem == null) return;

        //delete foodItem
        if (foodItemController.deleteFoodItem(selectedFoodItem.getId()))
            System.out.println("** Food Item Deleted Successfully **");
        else System.out.println(Message.message + "Failed to delete the Food Item");
        System.out.print("Redirection to owner's HomePage...");
        Helper.runTimer(3);
    } //completed

    static void updateFoodItemAvailability(User user) {
        System.out.println("\n\n***********************");
        System.out.println("* Update Availability *");
        System.out.println("***********************\n");

        //select restaurant
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //select foodItem
        FoodItem selectedFoodItem = selectFoodItemFromListOf(selectedRestaurant.getFoodItems());
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
    } //completed

    static void showAllOrders(User user) {
        System.out.println("\n\n******************");
        System.out.println("* Orders Section *");
        System.out.println("******************\n");

        //select restaurant
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //show orders
        System.out.println("\n\n******************************");
        System.out.println("* Restaurant's Order History *");
        System.out.println("******************************\n");

        showOrdersFromListOf(orderController.getOrdersByRestaurantId(selectedRestaurant.getId()));
        System.out.print("Press enter to go back: ");
        sc.nextLine();
    } // completed

    static void showPendingOrders(User user) {
        System.out.println("\n\n**************************");
        System.out.println("* Pending Orders Section *");
        System.out.println("**************************\n");
        //select restaurant
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //show orders
        System.out.println("\n\n******************");
        System.out.println("* Pending Orders *");
        System.out.println("******************\n");

        showOrdersFromListOf(orderController.getPendingOrdersByRestaurantId(selectedRestaurant.getId()));
        System.out.print("Press enter to go back: ");
        sc.nextLine();
    } //completed

    static void showInProgressOrders(User user) {
        System.out.println("\n\n******************************");
        System.out.println("* In_Progress Orders Section *");
        System.out.println("******************************\n");

        //select restaurant
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //show orders
        System.out.println("\n\n**********************");
        System.out.println("* In_Progress Orders *");
        System.out.println("**********************\n");

        showOrdersFromListOf(orderController.getInProgressOrdersByRestaurantId(selectedRestaurant.getId()));
        System.out.print("Press enter to go back: ");
        sc.nextLine();
    } //completed

    static void showCompletedOrders(User user) {
        System.out.println("\n\n****************************");
        System.out.println("* Completed Orders Section *");
        System.out.println("****************************\n");

        //select restaurant
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
        if (selectedRestaurant == null) return;

        //show orders
        System.out.println("\n\n********************");
        System.out.println("* Completed Orders *");
        System.out.println("********************\n");

        showOrdersFromListOf(orderController.getCompletedOrdersByRestaurantId(selectedRestaurant.getId()));
        System.out.print("Press enter to go back: ");
        sc.nextLine();
    } //completed

    static void updateOrderStatus(User user) {
        System.out.println("\n\n********************************");
        System.out.println("* Update Orders Status Section *");
        System.out.println("********************************\n");

        //select restaurant
        Restaurant selectedRestaurant = selectRestaurantFromListOf(restaurantController.getRestaurantsByOwnerId(user.getId()));
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

            selectedOrder = selectOrderFromListOf(orderController.getPendingOrdersByRestaurantId(selectedRestaurant.getId()));
            if (selectedOrder == null) return;
        }

        if (opt1 == 2) {
            //select an in_progress order
            System.out.println("\n\n**********************");
            System.out.println("* In_Progress Orders *");
            System.out.println("**********************\n");

            selectedOrder = selectOrderFromListOf(orderController.getInProgressOrdersByRestaurantId(selectedRestaurant.getId()));
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
    } //completed

    //************ Some useful functions *************

    static void showOrdersFromListOf(ArrayList<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("!! NO Orders found !!");
            System.out.print("Press enter to go back: ");
            sc.nextLine();
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
            System.out.printf("* Total Order Value = Rs-%-40f *\n", order.getTotalPrice());
            System.out.println("*******************************************************************");
        }
        System.out.println("Total Orders: " + (c - 1));
    }

    static void showRestaurantsFromListOf(ArrayList<Restaurant> restaurants) {
        if (restaurants.isEmpty()) {
            System.out.println("!!! NO Restaurants Found !!!");
            System.out.print("press enter to go back: ");
            sc.nextLine();
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

    static float calculateOrderValue(ArrayList<FoodItem> selectedFoodItems) {
        float orderValue = 0;
        for (FoodItem f : selectedFoodItems) {
            orderValue += f.getPrice();
        }
        return orderValue;
    }

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
        System.out.printf("%-15s %6s    %s\n", "Name", "Price", "Description");
        int c = 1;
        for (FoodItem foodItem : foodItems) {
            System.out.printf("%d. %-15s %-6s %s\n", c, foodItem.getName(), foodItem.getPrice(), foodItem.getDescription());
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
        showOrdersFromListOf(orders);

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


//TODO: consider foodItem availability while using it