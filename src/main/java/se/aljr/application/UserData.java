package se.aljr.application;

import se.aljr.application.exercise.Excercise.Exercise;

import java.util.HashSet;

public class UserData {
    private static String userName;
    private static int userHeight;
    private static int userAge;
    private static float userWeight;
    private static String userEmail;
    private static HashSet<Exercise> favoriteExercises = new HashSet<>();
    private static HashSet<Exercise> createdExercises = new HashSet<>();

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


    public static HashSet<Exercise> getFavoriteExercises() {
        return (favoriteExercises != null) ? favoriteExercises : new HashSet<>();
    }

    public static boolean removeFavoriteExercises(Exercise exercise) {
        if (favoriteExercises.contains(exercise)) {
            favoriteExercises.remove(exercise);
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean setFavoriteExercises(Exercise exercise) {
        if (!favoriteExercises.contains(exercise)) {
            favoriteExercises.add(exercise);
            return true;
        }
        else {
            return false;
        }
    }

    public static HashSet<Exercise> getCreatedExercises() {
        return (createdExercises != null) ? createdExercises : new HashSet<>();
    }

    public static void setCreatedExercises(Exercise exercise) {
        createdExercises.add(exercise);
    }
    }
