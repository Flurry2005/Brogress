package se.aljr.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NutritionCalculatorTest {

    @Test
    void getBmi() {
        UserData.setUserGender("Male");
        float weight = 70f; // in kg
        int height = 175;   // in cm

        float expectedBmi = 22.9f;

        float actualBmi = NutritionCalculator.getBmi(weight, height);

        assertEquals(expectedBmi, actualBmi, 0.1, "The calculated BMI should be correct");
    }

    @Test
    void getBMR() {
        UserData.setUserGender("Male");
        float weight = 70f;
        int height = 175;
        int age = 25;

        int expectedBmr = 1724;

        int actualBmr = NutritionCalculator.getBMR(weight, height, age);

        assertEquals(expectedBmr, actualBmr, "The calculated BMR should be correct");
    }

    @Test
    void getTDEE() {
        float weight = 70f;
        int height = 175;
        int age = 25;
        float activityFactor = 1.55f;

        int expectedTdee = (int) (1724 * 1.55);

        int actualTdee = NutritionCalculator.getTDEE(weight, height, age, activityFactor);


        assertEquals(expectedTdee, actualTdee, "The calculated TDEE should be correct");
    }

    @Test
    void getProteinNeed() {
        float weight = 70f;

        String expectedProteinNeed = "112.0g-154.0g";

        String actualProteinNeed = NutritionCalculator.getProteinNeed(weight);

        assertEquals(expectedProteinNeed, actualProteinNeed, "The calculated protein need range should be correct");
    }
}