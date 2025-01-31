package se.aljr.application.programplanner.workout;
import se.aljr.application.exercise.Excercise.Exercise;

import java.util.HashMap;

public class Workout {
    private HashMap<Exercise, HashMap> workoutdata;

    public Workout() {
        this.workoutdata = new HashMap<>();

    }

    public void setRep(Exercise excerciseName, Integer currentSetNumber, Integer repAmount, Integer weightAmount) {
        HashMap<Integer, HashMap> setNumber = new HashMap<>();
        HashMap<Integer, Integer> setData = new HashMap<>();
        setData.put(repAmount, weightAmount);
        setNumber.put(currentSetNumber, setData);
        workoutdata.put(excerciseName, setNumber);
    }

    public String getWorkoutData() {
        return this.workoutdata.toString();
    }
}