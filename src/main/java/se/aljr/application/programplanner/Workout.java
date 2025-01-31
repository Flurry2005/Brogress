package se.aljr.application.programplanner;

import se.aljr.application.exercise.Excercise.Exercise;

import java.util.ArrayList;
import java.util.HashMap;

public class Workout {
    String name = "Untitled Workout";
    static HashMap<Exercise, Set> exercisesDone = new HashMap<>();

    public String getName () {
        return this.name;
    }
    public static class Set {
        HashMap<Rep, Weight> set = new HashMap<>();

        public void addSet (Exercise exercise, Rep rep, Weight weight) {
            set.put(rep, weight);
            exercisesDone.put(exercise, this);
        }
    }
    public class Rep {
        int reps;
        public Rep (int repAmount) {
            this.reps = repAmount;
        }
    }
    public class Weight {
        int weight;
        public Weight (int weightAmount) {
            this.weight = weightAmount;
        }
    }
}