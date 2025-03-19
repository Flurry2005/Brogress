package se.aljr.application;

import se.aljr.application.exercise.Excercise.Exercise;
import se.aljr.application.loginpage.FirebaseManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

public class UserData {
    private static String userName;
    private static int userHeight;
    private static int userAge;
    private static float userWeight;
    private static String userGender;
    private static String userEmail;
    private static String userTheme;
    private static float activityFactor;
    private static HashSet<Exercise> favoriteExercises = new HashSet<>();
    private static final ArrayList<Exercise> createdExercises = new ArrayList<>();
    private static boolean isOnline;

    private static boolean isAdmin;

    public static void setUserName(String userName) {
        UserData.userName = userName;
    }

    public static String getUserName() {
        return UserData.userName;
    }

    public static void setUserHeight(int height) {
        UserData.userHeight = height;
    }

    public static int getUserHeight() {
        return UserData.userHeight;
    }

    public static void setUserAge(int age) {
        UserData.userAge = age;
    }

    public static int getUserAge() {
        return UserData.userAge;
    }

    public static void setUserWeight(float weight) {
        UserData.userWeight = weight;
    }

    public static float getUserWeight() {
        return UserData.userWeight;
    }

    public static void setEmail(String email) {
        UserData.userEmail = email;
    }

    public static String getEmail() {
        return UserData.userEmail;
    }

    public static void setTheme(String theme){UserData.userTheme = theme;}

    public static String getTheme(){return UserData.userTheme;}

    public static HashSet<Exercise> getFavoriteExercises() {
        return favoriteExercises;
    }

    public static void setFavoriteExercises(HashSet<Exercise> list) {
        favoriteExercises = list;
    }

    public static void removeFavoriteExercises(Exercise exercise) {
        favoriteExercises.remove(exercise);
    }
    public static void addFavoriteExercise(Exercise exercise) {
        favoriteExercises.add(exercise);
    }

    public static void updateFavoriteExercises() throws IOException, ExecutionException, InterruptedException, ClassNotFoundException {
        HashSet<Exercise> temp = FirebaseManager.readDBfavoriteExercises();
        favoriteExercises.clear();
        favoriteExercises.addAll(temp);
    }
    public static void updateCreatedExercise() throws IOException, ExecutionException, ClassNotFoundException, InterruptedException {
        ArrayList <Exercise> temp = FirebaseManager.readDBcreatedExercises();
        createdExercises.clear();
        createdExercises.addAll(temp);
    }
    public static ArrayList<Exercise> getCreatedExercises() {
        return (createdExercises != null) ? createdExercises : new ArrayList<>();
    }

    public static void setCreatedExercises(Exercise exercise) {
        createdExercises.add(exercise);
    }

    public static boolean isIsOnline() {
        return isOnline;
    }

    public static void setIsOnline(boolean isOnline) {
        UserData.isOnline = isOnline;
    }

    public static void setAdmin(boolean dbVerify) {
        isAdmin = dbVerify;
    }
    public static boolean isUserAdmin () {
        return isAdmin;
    }

    public static float getActivityFactor() {
        return activityFactor;
    }

    public static void setActivityFactor(float activityFactor) {
        UserData.activityFactor = activityFactor;
    }

    public static String getUserGender() {
        return userGender;
    }

    public static void setUserGender(String userGender) {
        UserData.userGender = userGender;
    }
}
