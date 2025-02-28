package se.aljr.application;

public class NutritionCalculator {
    public static float getBmi(float weight, int height){
        return (float) Math.round((float) ((weight) / Math.pow(((double) height / 100), 2)) * 10) /10;
    }
    public static int getBMR(float weight, int height, int age){
        return (int) (88.362+(13.397*weight)+(4.799*height)-(5.677*age));
    }
    public static int getTDEE(float weight, int height, int age,float activityFactor){
        return (int) (getBMR(weight,height,age)*activityFactor);
    }
    public static String getProteinNeed(float weight){
        return String.format("%.1fg-%.1fg",weight*1.6,weight*2.2).replace(",",".");
    }
}
