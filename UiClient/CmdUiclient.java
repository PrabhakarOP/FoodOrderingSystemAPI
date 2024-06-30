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
    static UserController userController=UserController.getInstance();
    static RestaurantController restaurantController=RestaurantController.getInstance();
    static FoodItemController foodItemController=FoodItemController.getInstance();
    static OrderController orderController=OrderController.getInstance();

    static Helper helper=new Helper();
    static Scanner sc=new Scanner(System.in);


    static{
        //populate customer
        userController.register(new User("Prince Prabhakar","1234","playhorn.pp@gmail.com","Customer"));
        userController.register(new User("Alok Kumar","1234","yuviyuvrajy9@gmail.com","Customer"));
        userController.register(new User("Sumit kumar","1234","sumitkumar@gmail.com","Customer"));

        //populate Owner
        userController.register(new User("Raushan kumar","1234","raushan22july@gmail.com","Owner"));
        userController.register(new User("Suraj kumar","1234","suraj77@gmail.com","Owner"));

        //populate restaurants
        User ownerRaushan=userController.login("raushan22july@gmail.com","1234");
        User ownerSuraj=userController.login("suraj77@gmail.com","1234");
        restaurantController.createRestaurant(new Restaurant(ownerRaushan.getId(),"Asli Momo","Raja bazar","6205221206"));
        restaurantController.createRestaurant(new Restaurant(ownerSuraj.getId(),"Pet Puja","Staion road","7717772453"));

        //populate foodItems
        Restaurant raushanRestaurant=restaurantController.getRestaurantByPhoneNumber("6205221206");
        FoodItem vegMomo=new FoodItem(raushanRestaurant.getId(),"Veg Momo","veggies filled dough dumplings",40);
        FoodItem springRoll=new FoodItem(raushanRestaurant.getId(),"Spring Roll","Veggies filled crunchy rolls",30);
        FoodItem paneerMomo=new FoodItem(raushanRestaurant.getId(),"Paneer Momo","Paneer filled dumplings",50);
        FoodItem chickenMomo=new FoodItem(raushanRestaurant.getId(), "Chicken Momo","Granulated chicken filled dumplings",60);
            //add the foodItems to the restaurant
            foodItemController.addFoodItem(raushanRestaurant.getId(),vegMomo);
            foodItemController.addFoodItem(raushanRestaurant.getId(),springRoll);
            foodItemController.addFoodItem(raushanRestaurant.getId(),paneerMomo);
            foodItemController.addFoodItem(raushanRestaurant.getId(),chickenMomo);
    }



    public static void main(String[] args) {


        System.out.println("\n\n**********************************************************");
        System.out.println("*              Welcome To Food Ordering App              *");
        System.out.println("**********************************************************");

        while(true) {
            System.out.println("\n***************");
            System.out.println("*  HOME PAGE  *");
            System.out.println("***************\n");

            System.out.println("Options:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit\n");

            System.out.print("Enter your option: ");
            int opt=0;

            try {
                opt = sc.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.print("");
            }

            sc.nextLine();                           // To remove new Line character from input stream.

            switch (opt){
                case 1: register();break;
                case 2: login();break;
                case 3: return;
                default:
                    System.out.println("Invalid option!");
                    System.out.print("press Enter to go to home page : ");
                    sc.nextLine();
            }
        }
    } //completed
    static void register(){


        System.out.println("\n*********************");
        System.out.println("* REGISTRATION PAGE *");
        System.out.println("****************");


        System.out.print("Register as restaurant owner (Y/N): ");
        String opt=sc.nextLine();
        String role;

        if(opt.equalsIgnoreCase("y"))
            role="Owner";
        else if(opt.equalsIgnoreCase("n"))
            role="Customer";
        else {
            System.out.println("Invalid Input!  redirecting to HOME PAGE--> .");
            return;
        }

        System.out.print("Enter Name: ");
        String name= helper.formatName(sc.nextLine());
        if(name.equalsIgnoreCase("q"))                  //Aborts the current process and back to previous menu.
            return;


        while(!helper.isNameValid(name)){
            System.out.print(Message.message+"\nPlease Enter a valid name: ");
            name= helper.formatName(sc.nextLine());
            if(name.equalsIgnoreCase("q"))              //Aborts the current process and back to previous menu.
                return;
        }

        System.out.print("Enter Email: ");
        String email= helper.formatEmail(sc.nextLine());
        if(email.equalsIgnoreCase("q"))                  //Aborts the current process and back to previous menu.
            return;


        while(!helper.isEmailValid(email)){
            System.out.print(Message.message+"\nPlease Enter a valid email: ");
            email= helper.formatEmail(sc.nextLine());
            if(email.equalsIgnoreCase("q"))                  //Aborts the current process and back to previous menu.
                return;
        }


        //****phoneNumber not needed*********
//        System.out.print("Enter Phone Number: ");
//        String phoneNumber= helper.formatPhoneNumber(sc.nextLine());
//        if(phoneNumber.equalsIgnoreCase("q"))           //Aborts the current process and back to previous menu.
//            return;
//
//        while(!helper.isPhoneNumberValid(phoneNumber)){
//            System.out.print(Message.message+"\nPlease enter valid phone number: ");
//            phoneNumber= helper.formatPhoneNumber(sc.nextLine());
//            if(phoneNumber.equalsIgnoreCase("q"))         //Aborts the current process and back to previous menu.
//                return;
//        }


        System.out.print("Enter your password: ");
        String password=sc.nextLine();
        if(password.equalsIgnoreCase("q"))
            return;
        while(password==""){
            System.out.print("Enter your password: ");
            password=sc.nextLine();
            if(password.equalsIgnoreCase("q"))
                return;
        }

        //Now signUP
        User newUser=new User(name,password,email,role);
        if(userController.register(newUser))
            System.out.println("<--------signed Up successfully.Now you can signIn.------->");
        else
            System.out.println(Message.message + " ! SignUp failed !  Try again....");

        System.out.print("press Enter to go to home page : ");
        sc.nextLine();

    } //completed
    static void login(){


        System.out.println("\n****************");
        System.out.println("*  LOGIN PAGE  *");
        System.out.println("****************\n");

        //email
        System.out.print("Enter registered email: ");
        String email= helper.formatEmail(sc.nextLine());
        if(email.equalsIgnoreCase("q"))                  //Aborts the current process and back to previous menu.
            return;


        while(!helper.isEmailValid(email)){
            System.out.print(Message.message+"\nPlease Enter a valid email: ");
            email= helper.formatEmail(sc.nextLine());
            if(email.equalsIgnoreCase("q"))                  //Aborts the current process and back to previous menu.
                return;
        }

        //password
        System.out.print("Enter your password: ");
        String password=sc.nextLine();
        if(password.equalsIgnoreCase("q"))
            return;
        while(password==""){
            System.out.print("Enter your password: ");
            password=sc.nextLine();
            if(password.equalsIgnoreCase("q"))
                return;
        }
        //now login
        User user=userController.login(email,password);

        if(user==null){
            System.out.print(Message.message+" Login failed....Redirecting to homepage");
            helper.runTimer(5);
            return;
        }

        if(user.getRole().equalsIgnoreCase("customer"))
            customerHomePage(user);
        else
            ownerHomePage(user);
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
                    helper.runTimer(5);
            }
        }
    }
    static void ownerHomePage(User user){
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
            System.out.println("8.  View Orders Status");
            System.out.println("9.  View PENDING orders");
            System.out.println("10. View Orders IN_PROGRESS");
            System.out.println("11. View COMPLETED Orders");
            System.out.println("12. Log Out");
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
                    viewOrderHistory(user);
                    break;
                case 4:
                    return;
                default:
                    System.out.print("!!!Invalid Input!!!");
                    System.out.print(" please wait");
                    helper.runTimer(3);
            }
        }
    }

    //************* customer functions ***************
    static void viewCustomerProfile(User user){
        System.out.println("\n********************");
        System.out.println("* Customer Profile *");
        System.out.println("********************\n");

        System.out.printf("%-15s %s\n","Id:",user.getId());
        System.out.printf("%-15s %s\n","Name:",user.getUsername());
        System.out.printf("%-15s %s\n","Email:",user.getEmail());
        //calculate total orders
        int totalOrders;
        if(orderController.getOrdersByCustomerId(user.getId()).isEmpty())
            totalOrders=0;
        else
            totalOrders=orderController.getOrdersByCustomerId(user.getId()).size();
        System.out.printf("%-15s %d\n","Total Orders:",totalOrders);

        System.out.print("Press enter to go back: ");
        sc.nextLine();
    } //completed
    static void placeOrder(User user){
        System.out.println("\n***************");
        System.out.println("* Place Order *");
        System.out.println("***************\n");

        //show available restaurants
        if(restaurantController.getAllRestaurants().isEmpty()) {
            System.out.print("!!! No restaurants available !!! Going back , Please wait");
            helper.runTimer(3);
            return;
        }
        showAvailableRestaurants();

        //choose restaurant
        int opt=0;
        while (true) {
            System.out.print("Choose restaurant number: ");
            try {
                opt = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }
            sc.nextLine();                                  //remove new line character

            if (opt == -1)
                return;
            else if (opt < 1 || opt > restaurantController.getAllRestaurants().size()) {
                System.out.print("!!!! Invalid option !!!!");
                System.out.print("Enter  q to abort , r to show available restaurants , anything to try again: ");
                String in=sc.nextLine();
                if(in.equalsIgnoreCase("q"))
                    return;
                else if(in.equalsIgnoreCase("r"))
                    showAvailableRestaurants();
            }
            else
                break;
        }
        Restaurant selectedRestaurant=restaurantController.getAllRestaurants().get(opt-1);

        //show foodItem list
        if(selectedRestaurant.getFoodItems().isEmpty()){
            System.out.print("This restaurant has no food Item available......Redirecting to customer homepage");
            helper.runTimer(3);
            return;
        }
        System.out.println("***** Available FooItems ***** ");
        showFoodItemListOf(selectedRestaurant);

        //choose foodItems
        ArrayList<FoodItem> selectedFoodItems=new ArrayList<>();
        int opt1=0;
        while (true) {
            System.out.print("Choose foodItem number: ");
            try {
                opt1 = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }
            sc.nextLine();                                  //remove new line character

            if (opt1 == -1)
                return;
            else if (opt1 < 1 || opt1 > selectedRestaurant.getFoodItems().size()) {
                System.out.print("!!!! Invalid option !!!!");
                System.out.print("Enter  q to abort , f to show available foodItems , anything to try again: ");
                String in=sc.nextLine();
                if(in.equalsIgnoreCase("q"))
                    return;
                else if(in.equalsIgnoreCase("f"))
                    showFoodItemListOf(selectedRestaurant);
            }
            else{
                selectedFoodItems.add(selectedRestaurant.getFoodItems().get(opt1-1));
                System.out.print("Do you want to add more(y/n): ");
                String  in=sc.nextLine();
                if( in.equalsIgnoreCase("y"))
                    continue;
                else break;
            }
        }
        //place order
        float orderValue=calculateOrderValue(selectedFoodItems);
        System.out.println("Your order value: Rs-"+orderValue);
        System.out.print("Confirm to place order (y/n): ");
        String  in=sc.nextLine();
        if(in.equalsIgnoreCase("y")) {
            Order order=new Order(user.getId(), selectedRestaurant.getId(),selectedFoodItems);
            if(orderController.placeOrder(order))
                System.out.println("** Your Order is placed **");
            else
                System.out.println("!!! something went wrong try again later !!!");
        }
        else{
            System.out.print("Cancelling your order. please wait");
            helper.runTimer(3);
        }
    }//completed
    static void viewOrderHistory(User user){
        System.out.println("\n\n**********************");
        System.out.println("* Your Order History *");
        System.out.println("**********************\n");

        ArrayList<Order> myOrders=orderController.getOrdersByCustomerId(user.getId());
        if(myOrders.isEmpty()) {
            System.out.println("!! You have no orders !!");
            System.out.print("Press enter to go back: ");
            sc.nextLine();
            return;
        }
        int c=1;
        for(Order order: myOrders){
            System.out.println();
            System.out.println("Order no: "+c);c++;
            System.out.println("*******************************************************************");
            System.out.println("* Order Id: "+order.getId()+"                Status: "+order.getStatus()+ "               *");
            System.out.printf("* Restaurant Name: %-46s *\n",restaurantController.getRestaurantByRestaurantId(order.getRestaurantId()).getName());
            System.out.println("*                                                                 *");
            System.out.println("*                        ** Food Items **                         *");
            System.out.println("*                                                                 *");
            System.out.printf("* %-15s %6s    %-37s *\n","Name","Price","Description");
            int fc=1;
            for(FoodItem foodItem: order.getFoodItems()){
                System.out.printf("* %d. %-15s %-6s %-37s *\n",fc,foodItem.getName(),foodItem.getPrice(),foodItem.getDescription());
                fc++;
            }
            System.out.println("*                                                                 *");
            System.out.printf("* Total Order Value = Rs-%-40f *\n",order.getTotalPrice());
            System.out.println("*******************************************************************");
        }
        System.out.println("Total Orders: "+(c-1));
        System.out.print("Press enter to go back: ");
        sc.nextLine();
    }//complete

    //************ owner functions **************
    static void viewOwnerProfile(User user){
        System.out.println("\n*****************");
        System.out.println("* Owner Profile *");
        System.out.println("*****************\n");

        System.out.printf("%-15s %s\n","Id:",user.getId());
        System.out.printf("%-15s %s\n","Name:",user.getUsername());
        System.out.printf("%-15s %s\n","Email:",user.getEmail());
        System.out.println();
        System.out.printf("%15s** Your Restaurants **\n"," ");
        System.out.printf("%-29s %12s    Address","   Name","Phone Number");
        ArrayList<Restaurant> ownerRestaurants=restaurantController.getRestaurantsByOwnerId(user.getId());

        if(ownerRestaurants.isEmpty()){
            System.out.println("!!! You have not created any restaurants Yet !!!");
            System.out.print("press enter to go back: ");
            sc.nextLine();
        }

        int c=1;
        for(Restaurant restaurant: ownerRestaurants){
            System.out.printf("%-3d. %-25s %-12s    %s\n",c,restaurant.getName(),restaurant.getPhone(),restaurant.getAddress());
            c++;
        }
        System.out.println("Total Restaurants: "+(c-1));

        System.out.println("press enter to go back: ");
        sc.nextLine();
    } //completed
    static void createRestaurant(User user){
        System.out.println("\n\n***********************");
        System.out.println("* Create A Restaurant *");
        System.out.println("***********************\n");
        //Input name
        System.out.print("Enter Restaurant name: ");
        String name=helper.formatName(sc.nextLine());
        if(name.equalsIgnoreCase("q"))                          //aborts the process
            return;

        while (!helper.isNameValid(name)){
            System.out.println(Message.message + " please enter a valid name");
            name=helper.formatName(sc.nextLine());
            if(name.equalsIgnoreCase("q"))                      //aborts the process
                return;
        }
        //Input address
        System.out.print("Enter Restaurant Address: ");
        String address= helper.formatAddress(sc.nextLine());
        if(address.equalsIgnoreCase("q"))                       //aborts the process
            return;

        while (!helper.isAddressValid(address)){
            System.out.println(Message.message + " please enter a valid address");
            address= helper.formatAddress(sc.nextLine());
            if(address.equalsIgnoreCase("q"))                   //aborts the process
                return;
        }

        //Input Phone Number
        System.out.print("Enter Restaurant Phone Number: ");
        String phoneNumber=helper.formatPhoneNumber(sc.nextLine());
        if(name.equalsIgnoreCase("q"))                          //aborts the process
            return;

        while (!helper.isPhoneNumberValid(phoneNumber)){
            System.out.println(Message.message + " please enter a valid phone Number");
            phoneNumber=helper.formatPhoneNumber(sc.nextLine());
            if(name.equalsIgnoreCase("q"))                          //aborts the process
                return;
        }

        //Create Restaurant
        Restaurant newRestaurant=new Restaurant(user.getId(),name,address,phoneNumber);
        if(restaurantController.createRestaurant(newRestaurant))
            System.out.println("   ** Restaurant Created **   ");
        else
            System.out.println(Message.message + "!!! Failed to Create Restaurant , Try again !!!");
        System.out.print("press enter to continue: ");
        sc.nextLine();
    } //completed
    static void updateRestaurant(User user){
        System.out.println("\n\n**************************");
        System.out.println("* Update Your Restaurant *");
        System.out.println("**************************\n");

        //show restaurants
        showAvailableRestaurantsByOwnerId(user.getId());
        if(restaurantController.getRestaurantsByOwnerId(user.getId()).isEmpty()){
            System.out.print("press enter to go back: ");
            sc.nextLine();
        }
        //choose restaurant
        int opt=0;
        while (true) {
            System.out.print("Choose restaurant number: ");
            try {
                opt = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("");
            }
            sc.nextLine();                                  //remove new line character

            if (opt == -1)                                  // aborts the process
                return;
            else if (opt < 1 || opt > restaurantController.getRestaurantsByOwnerId(user.getId()).size()) {
                System.out.print("!!!! Invalid option !!!!");
                System.out.print("Enter  q to abort , r to show available restaurants , anything to try again: ");
                String in=sc.nextLine();
                if(in.equalsIgnoreCase("q"))            //aborts the process
                    return;
                else if(in.equalsIgnoreCase("r"))
                    showAvailableRestaurantsByOwnerId(user.getId());
            }
            else
                break;
        }
        Restaurant selectedRestaurant=restaurantController.getRestaurantsByOwnerId(user.getId()).get(opt-1);
        Restaurant updatedRestaurant;


        System.out.println("Choose What You Want to Update: ");
        System.out.println("1. Restaurant Name");
        System.out.println("2. Restaurant Address");
        System.out.println("3. Restaurant PhoneNumber");
        System.out.println("4. Exit");

        while(true){
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

            if(opt==1){
                //Enter new name
                String newName= helper.formatName(sc.nextLine());
                if(newName.equalsIgnoreCase("q"))           //abort the process
                    return;

                while (!helper.isNameValid(newName)){
                    System.out.println(Message.message + " Please Enter a valid Name");
                    newName= helper.formatName(sc.nextLine());
                    if(newName.equalsIgnoreCase("q"))               //abort the process
                        return;
                }
                     
            }

            switch (opt) {
                case 1:
                    //Enter new name
                    System.out.print("Enter new name: ");
                    String newName= helper.formatName(sc.nextLine());
                    if(newName.equalsIgnoreCase("q"))           //abort the process
                        return;

                    while (!helper.isNameValid(newName)){
                        System.out.println(Message.message + " Please Enter a valid Name");
                        newName= helper.formatName(sc.nextLine());
                        if(newName.equalsIgnoreCase("q"))               //abort the process
                            return;
                    }
                    //create updated restaurant
                    updatedRestaurant=
                    break;
                case 2:
                    createRestaurant(user);
                    break;
                case 3:
                    viewOrderHistory(user);
                    break;
                case 4:
                    return;
                default:
                    System.out.print("!!!Invalid Input!!!");
                    System.out.print(" please wait");
                    helper.runTimer(3);
            }
        }

    }

    //************ Some useful functions *************
    static void showAvailableRestaurants(){
        System.out.println("Available Restaurants: ");
        ArrayList<Restaurant> availableRestaurants=restaurantController.getAllRestaurants();

        if(availableRestaurants.isEmpty()) {
            System.out.print("!!! No restaurants available !!!");

            return;
        }

        int c=1;
        System.out.printf("%-18s %s\n","Restaurant Name","Food Items");
        for(Restaurant r: availableRestaurants){
            System.out.printf("%d. %-15s  %d\n",c,r.getName(),r.getFoodItems().size());
            c++;
        }
    }
    static void showAvailableRestaurantsByOwnerId(String ownerId){
        System.out.println("Available Restaurants: ");
        ArrayList<Restaurant> ownerRestaurants=restaurantController.getRestaurantsByOwnerId(ownerId);

        if(ownerRestaurants.isEmpty()){
            System.out.println("!!! No Restaurants available !!! Create a Restaurant First ");
            return;
        }

        int c=1;
        System.out.printf("%-18s %s\n","Restaurant Name","Food Items");
        for(Restaurant r: ownerRestaurants){
            System.out.printf("%d. %-15s  %d\n",c,r.getName(),r.getFoodItems().size());
            c++;
        }
    }
    static void showFoodItemListOf(Restaurant restaurant){
        ArrayList<FoodItem> foodItems=restaurant.getFoodItems();
        System.out.printf("%-15s %6s    %s\n","Name","Price","Description");
        int c=1;
        for(FoodItem foodItem: foodItems){
            System.out.printf("%d. %-15s %-6s %s\n",c,foodItem.getName(),foodItem.getPrice(),foodItem.getDescription());
            c++;
        }
    }
    static float calculateOrderValue(ArrayList<FoodItem> selectedFoodItems){
        float orderValue=0;
        for(FoodItem f: selectedFoodItems){
            orderValue+=f.getPrice();
        }
        return orderValue;
    }

}
