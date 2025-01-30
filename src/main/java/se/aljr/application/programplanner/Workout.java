package se.aljr.application.programplanner;

import se.aljr.application.exercise.Excercise.Exercise;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Workout {
    private String name;
    private HashMap<Exercise, LinkedHashMap <Integer, HashMap>> workout = new HashMap<>();
    private LinkedHashMap<Integer, HashMap> set;

    public Workout() {
        this.name = "Untitled Workout";
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public void addExercise(Exercise exercise) {
        workout.put(exercise, null);
    }

    public void addSet(Exercise exercise, Integer setNumber) {

        LinkedHashMap<Integer, HashMap> set = new LinkedHashMap<>();
        set.put(setNumber, new HashMap<>());
        workout.put(exercise, set);
    }

    public void addRep(Exercise exercise, Integer setNumber, Integer repNumber, Integer weight) {
        HashMap<Integer, Integer> reps = new HashMap<>();
        reps.put(repNumber, weight);
        set.put(setNumber, reps);
        workout.put(exercise, set);
    }

    public String getExercise() {
        return this.workout.toString();
    }
}