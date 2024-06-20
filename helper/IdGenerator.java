package helper;

import java.util.ArrayList;

public  class IdGenerator {
    private static final ArrayList<String> generatedUserIdList = new ArrayList<>();

    public  String generateUserId(){
        int id;
        if(generatedUserIdList.isEmpty())
            id=1;
        else{
            id=Integer.parseInt(generatedUserIdList.get(generatedUserIdList.size()-1))+1;  //get value at last index + 1
        }
        //convert the integer id to 8 digits string id.
        String newId=Integer.toString(id);
        generatedUserIdList.add(newId);

        int digitCount=countDigits(id);
        for(int i=1;i<=8-digitCount;i++)
            newId="0" + newId;

        return newId;
    }

    int countDigits(int n){
        int count=0;
        while(n>0){
            n=n/10;
            count++;
        }
        return count;
    }

}
