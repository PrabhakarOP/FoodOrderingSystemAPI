package helper;

public class Helper {
    // ***********************Validation Functions**********************************

    public boolean isPhoneNumberValid(String phoneNumber){
        //to check null input
        if(phoneNumber==null || phoneNumber.isEmpty()){
            Message.message="NO input detected!!";
            return false;
        }
        //check length
        if(phoneNumber.length()!=10){       //to check if 10 characters are available
            Message.message="Enter a valid 10 digits phone number";
            return false;
        }

        //check valid characters
        String validDigits="0123456789";

        for(int i=0;i<10;i++){               //to check if all the characters are digit
            char ch=phoneNumber.charAt(i);
            byte f=0;
            for(int j=0;j<10;j++){
                if(ch==validDigits.charAt(j)) {
                    f=1;break;
                }
            }
            if(f==0){
                Message.message="Enter digits only";
                return false;
            }
        }

        return true;
    }

    public boolean isNameValid(String name){
        //to check null input
        if(name==null || name.isEmpty()){
            Message.message="NO input detected";
            return false;
        }


        String validCharacters="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        //loop to check all character are either alphabet or space.
        for(int i=0;i<name.length();i++){
            char ch=name.charAt(i);
            byte f=0;
            for(int j=0;j<validCharacters.length();j++){
                if(ch==validCharacters.charAt(j)){
                    f=1;break;
                }
            }
            if(f==0){
                Message.message="Only alphabets are allowed!";
                return false;
            }
        }

        return true;
    }

    public boolean isEmailValid(String email){
        //check null input
        if(email==null || email==""){
            Message.message="NO input detected";
            return  false;
        }
        //match email pattern
        String regex="^[a-zA-z0-9._-]+@[a-z]+.com$";
        Message.message="not a valid email";
        return email.matches(regex);
    }


    // **********************Formatter Functions*************************************
    public String formatPhoneNumber(String phoneNumber){
        //check null input
        if(phoneNumber==null || phoneNumber.isEmpty())
            return phoneNumber;
        //remove extra spaces
        phoneNumber=phoneNumber.trim();
        phoneNumber=phoneNumber.replaceAll(" ","");
        return phoneNumber;
    } //completed

    public String formatName(String name){

        //check null
        if(name==null || name.isEmpty())
            return name;

        //remove extra spaces
        name=name.trim();
        name=name.replaceAll("\\s+"," ");

        //make first letter of each word capital
        name=name.toLowerCase();
        char ch=(char)(name.charAt(0)-32);
        name= ch + name.substring(1);
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)==' '){
                ch=(char)(name.charAt(i+1) - 32);
                name= name.substring(0,i+1) +ch + name.substring(i+2);
            }
        }

        return name;
    } //completed

    public String formatEmail(String email){
        //check for null input
        if(email==null || email=="")
            return email;
        //remove extra spaces
        email=email.trim();
        return email;
    }


    //********************** Some useful functions ********************

    public void runTimer(int seconds){
        for(int i=seconds;i>=0;i--){
            System.out.print('.');
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println();
            }
        }
    }

}

