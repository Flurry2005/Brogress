package se.aljr.application;

import com.google.cloud.storage.Acl;

public class UserData {
    private static String userName;
    private static int userHeight;
    private static int userAge;
    private static float userWeight;
    private static String userEmail;

    public static void setUserName(String userName){
        UserData.userName = userName;
    }

    public static String getUserName(){
        return UserData.userName;
    }

    public static void setUserHeight(int height){
        UserData.userHeight = height;
    }

    public static int getUserHeight(){
        return UserData.userHeight;
    }

    public static void setUserAge(int age){
        UserData.userAge = age;
    }

    public static int getUserAge(){
        return UserData.userAge;
    }

    public static void setUserWeight(float weight){
        UserData.userWeight = weight;
    }

    public static float getUserWeight() {
        return UserData.userWeight;
    }

    public static void setEmail(String email){
        UserData.userEmail = email;
    }

    public static String getEmail(){
        return UserData.userEmail;
    }
}
