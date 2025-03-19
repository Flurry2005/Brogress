package se.aljr.application;

import org.junit.jupiter.api.Test;
import se.aljr.application.exercise.Excercise.Exercise;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UserDataTest {

    @Test
    void setUserName() {
        String expectedUserName = "JohnDoe";
        UserData.setUserName(expectedUserName);
        System.out.println();
        assertEquals(expectedUserName, UserData.getUserName(), "The userName should match the expected value.");
    }

    @Test
    void setUserHeight() {
        int expectedHeight = 150;
        UserData.setUserHeight(expectedHeight);
        assertEquals(expectedHeight, UserData.getUserHeight());
    }

    @Test
    void setUserAge() {
        int expectedAge = 20;
        UserData.setUserAge(expectedAge);
        assertEquals(expectedAge, UserData.getUserAge());
    }

    @Test
    void setUserWeight() {
        int expectedWeight = 20;
        UserData.setUserWeight(expectedWeight);
        assertEquals(expectedWeight, UserData.getUserWeight());
    }

    @Test
    void setEmail() {
        String expectedEmail = "testmail@test.com";
        UserData.setEmail(expectedEmail);
        assertEquals(expectedEmail, UserData.getEmail());
    }

    @Test
    void setTheme() {
        String expectedTheme = "testTheme";
        UserData.setTheme(expectedTheme);
        assertEquals(expectedTheme, UserData.getTheme());
    }

    @Test
    void getFavoriteExercises() {
        Exercise exercise1 = new Exercise();
        Exercise exercise2 = new Exercise();

        exercise1.setName("Exercise1");
        exercise2.setName("Exercise2");

        UserData.addFavoriteExercise(exercise1);
        UserData.addFavoriteExercise(exercise2);

        HashSet<Exercise> result = UserData.getFavoriteExercises();

        assertEquals(3, result.size(), "The favoriteExercises collection should contain 2 exercises.");
        assertTrue(result.contains(exercise1) && result.contains(exercise2), "The returned collection should contain the added exercises.");
    }

    @Test
    void removeFavoriteExercises() {
        Exercise exercise = new Exercise();
        UserData.addFavoriteExercise(exercise);
        UserData.removeFavoriteExercises(exercise);
        assertFalse(UserData.getFavoriteExercises().contains(exercise), "The exercise should be removed from the favoriteExercises collection.");
    }

    @Test
    void addFavoriteExercise() {
        Exercise exercise = new Exercise();
        UserData.addFavoriteExercise(exercise);
        assertTrue(UserData.getFavoriteExercises().contains(exercise), "The exercise should be added to the favoriteExercises collection.");
    }

    @Test
    void setCreatedExercises() {
        Exercise exercise = new Exercise();
        UserData.setCreatedExercises(exercise);
        assertTrue(UserData.getCreatedExercises().contains(exercise),
                "The exercise should be added to the createdExercises collection.");
    }

    @Test
    void isIsOnline() {
        boolean expectedValue = true;
        UserData.setIsOnline(expectedValue);
        assertTrue(UserData.isIsOnline(), "The isOnline property should be set to true.");
    }

    @Test
    void setIsOnline() {
        boolean expectedValue = false;
        UserData.setIsOnline(expectedValue);
        assertFalse(UserData.isIsOnline(), "The isOnline property should be set to false.");
    }
}