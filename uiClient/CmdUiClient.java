package uiClient;

import controller.FoodItemController;
import controller.RestaurantController;
import controller.UserController;
import helper.Helper;
import helper.Message;
import model.FoodItem;
import model.Restaurant;
import model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CmdUiClient {
    static UserController userController = UserController.getInstance();
    static RestaurantController restaurantController = RestaurantController.getInstance();
    static FoodItemController foodItemController = FoodItemController.getInstance();

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
    }

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
        if (name.equalsIgnoreCase("q")) return;

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

    }

    static void login() {


        System.out.println("\n****************");
        System.out.println("*  LOGIN PAGE  *");
        System.out.println("****************\n");

        //email
        System.out.print("Enter registered email: ");
        String email = InputFunctions.inputEmail();
        if (email.equalsIgnoreCase("q")) return;                                     //aborts the process

        //password
        System.out.print("Enter your password: ");
        String password = InputFunctions.inputPassword();
        if (password.equalsIgnoreCase("q")) return;
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
                    CustomerFunctions.viewCustomerProfile(user);
                    break;
                case 2:
                    CustomerFunctions.placeOrder(user);
                    break;
                case 3:
                    CustomerFunctions.viewOrderHistory(user);
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
            System.out.println("6.  Show foodItems of a Restaurant");
            System.out.println("7.  Update foodItems of a Restaurant");
            System.out.println("8.  Delete foodItems of a Restaurant");
            System.out.println("9.  Update foodItem availability");
            System.out.println("10.  View all orders");
            System.out.println("11. View PENDING orders");
            System.out.println("12. View Orders IN_PROGRESS");
            System.out.println("13. View COMPLETED Orders");
            System.out.println("14. Update Order status");
            System.out.println("15. Log Out");
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
                    OwnerFunctions.viewOwnerProfile(user);
                    break;
                case 2:
                    OwnerFunctions.createRestaurant(user);
                    break;
                case 3:
                    OwnerFunctions.updateRestaurant(user);
                    break;
                case 4:
                    OwnerFunctions.deleteRestaurant(user);
                    break;
                case 5:
                    OwnerFunctions.addFoodItems(user);
                    break;
                case 6:
                    OwnerFunctions.showFoodItems(user);
                    break;
                case 7:
                    OwnerFunctions.updateFoodItem(user);
                    break;
                case 8:
                    OwnerFunctions.deleteFoodItem(user);
                    break;
                case 9:
                    OwnerFunctions.updateFoodItemAvailability(user);
                    break;
                case 10:
                    OwnerFunctions.showAllOrders(user);
                    break;
                case 11:
                    OwnerFunctions.showPendingOrders(user);
                    break;
                case 12:
                    OwnerFunctions.showInProgressOrders(user);
                    break;
                case 13:
                    OwnerFunctions.showCompletedOrders(user);
                    break;
                case 14:
                    OwnerFunctions.updateOrderStatus(user);
                    break;
                case 15:
                    return;
                default:
                    System.out.print("!!!Invalid Input!!!");
                    System.out.print(" please wait");
                    Helper.runTimer(3);
            }
        }
    }

}