package UiClient;

import helper.Helper;
import helper.Message;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputFunctions {
    static Scanner sc=new Scanner(System.in);

    public static String inputName() {
        String name = Helper.formatName(sc.nextLine());
        if (name.equalsIgnoreCase("q"))                  //Aborts the current process and back to previous menu.
            return "q";


        while (!Helper.isNameValid(name)) {
            System.out.print(Message.message + "\nPlease Enter a valid name: ");
            name = Helper.formatName(sc.nextLine());
            if (name.equalsIgnoreCase("q"))              //Aborts the current process and back to previous menu.
                return "q";
        }
        return name;
    }

    public static String inputEmail(){
        String email = Helper.formatEmail(sc.nextLine());
        if (email.equalsIgnoreCase("q"))                  //Aborts the current process and back to previous menu.
            return "q";


        while (!Helper.isEmailValid(email)) {
            System.out.print(Message.message + "\nPlease Enter a valid email: ");
            email = Helper.formatEmail(sc.nextLine());
            if (email.equalsIgnoreCase("q"))                  //Aborts the current process and back to previous menu.
                return "q";
        }
        return email;
    }

    public static String inputPassword(){
        String password = sc.nextLine();
        if (password.equalsIgnoreCase("q")) return "q";
        while (password == "") {
            System.out.print("Enter your password: ");
            password = sc.nextLine();
            if (password.equalsIgnoreCase("q")) return "q";
        }
        return password;
    }

    public static String inputAddress(){
        String address = Helper.formatAddress(sc.nextLine());
        if (address.equalsIgnoreCase("q"))                       //aborts the process
            return "q";

        while (!Helper.isAddressValid(address)) {
            System.out.println(Message.message + " please enter a valid address");
            address = Helper.formatAddress(sc.nextLine());
            if (address.equalsIgnoreCase("q"))                   //aborts the process
                return "q";
        }
        return address;
    }

    public static String inputPhoneNumber(){
        String phoneNumber = Helper.formatPhoneNumber(sc.nextLine());
        if (phoneNumber.equalsIgnoreCase("q"))                          //aborts the process
            return "q";

        while (!Helper.isPhoneNumberValid(phoneNumber)) {
            System.out.println(Message.message + " please enter a valid phone Number");
            phoneNumber = Helper.formatPhoneNumber(sc.nextLine());
            if (phoneNumber.equalsIgnoreCase("q"))                          //aborts the process
                return "q";
        }
        return phoneNumber;
    }

    public static String inputDescription(){
        String description = Helper.formatDescription(sc.nextLine());
        if (description.equalsIgnoreCase("q"))           //abort the process
            return "q";

        while (!Helper.isDescriptionValid(description)) {
            System.out.println(Message.message + " Please Enter a valid Name");
            description = Helper.formatDescription(sc.nextLine());
            if (description.equalsIgnoreCase("q"))               //abort the process
                return "q";
        }
        return description;
    }

    public static float inputPrice(){
        float price = 0;
        while (price <= 0) {
            System.out.print("Enter Price: ");
            try {
                price = sc.nextFloat();
            } catch (InputMismatchException e) {
                System.out.println();
            }
            sc.nextLine();
            if (price == -1) return -1;
            else if (price <= 0) {
                System.out.println("please enter a valid price. Or enter -1 to abort the process.");
                Helper.runTimer(2);
            }
        }
        return price;
    }
}
