package helper;

import java.util.ArrayList;

public  class IdGenerator {
    private static final ArrayList<String> generatedUserIdList = new ArrayList<>();
    private static final ArrayList<String> generatedRestaurantIdList = new ArrayList<>();
    private static final ArrayList<String> generatedOrderIdList = new ArrayList<>();
    private static final ArrayList<String> generatedFoodItemIdList= new ArrayList<>();

    public static String generateUserId(){
       return generateIdFor(generatedUserIdList);
    }

    public static String generateRestaurantId(){
       return generateIdFor(generatedRestaurantIdList);
    }

    public static String generateOrderId(){
        return generateIdFor(generatedOrderIdList);
    }

    public static String generateFoodItemId(){
        return generateIdFor(generatedFoodItemIdList);
    }


//************* extra useful functions ******************
    
    static int countDigits(int n){
        int count=0;
        while(n>0){
            n=n/10;
            count++;
        }
        return count;
    }

    static String generateIdFor(ArrayList<String> list){
        int id;
        if(list.isEmpty())
            id=1;
        else
            id=Integer.parseInt(list.get( list.size()-1) ) +1;

        //convert the integer id to 8 digit string id
        String newId=Integer.toString(id);
        list.add(newId);

        //adding leading zeros
        int digitCount=countDigits(id);
        for(int i=1;i<=8-digitCount;i++)
            newId="0" + newId;

        return newId;
    }

}
