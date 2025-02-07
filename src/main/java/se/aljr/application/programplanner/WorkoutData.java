package se.aljr.application.programplanner;

import java.io.Serializable;
import java.util.*;

public class WorkoutData implements Serializable {
    private String title = "Untitled Workout";
    private HashMap<Integer, List<WorkoutSet>> exerciseSets = new HashMap<>();

    private int totalWorkoutHeight;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void addSet(int exerciseId, WorkoutSet newSet) {

        exerciseSets.putIfAbsent(exerciseId, new ArrayList<>());
        List<WorkoutSet> sets = exerciseSets.get(exerciseId);
        boolean found = false;
        for (WorkoutSet set : sets) {
            if (set.getNumber() == newSet.getNumber()) {
                set.setExercise(newSet.getExercise());
                set.setNumber(newSet.getNumber());
                set.setRir(newSet.getRir());
                set.setWeight(newSet.getWeight());
                set.setReps(newSet.getReps());
                found = true;
                break;
            }
        }
        if (!found) {
            sets.add(newSet);
        }
    }

    public void updateSet(int exerciseId, List<WorkoutSet> sets) {
        // update and assign setnumbers
        int newSetId = 1;
        for (WorkoutSet s : sets) {
            s.setNumber(newSetId++);
            if (sets.isEmpty()) {
                exerciseSets.remove(exerciseId);
            }
        }
    }

    public void deleteSet(int exerciseId, int setNumber) {
        List<WorkoutSet> sets = exerciseSets.get(exerciseId);
        sets.removeIf(set -> set.getNumber() == setNumber);
        updateSet(exerciseId, sets);
    }

    public void moveSetUp(int exerciseId, WorkoutSet workoutSet) {
        if (workoutSet.getNumber() > 1) {
            Collections.swap(exerciseSets.get(exerciseId), workoutSet.getNumber() - 2, workoutSet.getNumber()-1);
        }
    }

    public void moveSetDown(int exerciseId, WorkoutSet workoutSet, WorkoutData currentWorkout) {
        if (workoutSet.getNumber() < currentWorkout.getSetSize(exerciseId)) {
            Collections.swap(exerciseSets.get(exerciseId), workoutSet.getNumber() - 1, workoutSet.getNumber());
        }
    }

    public int getSetSize(int id) {
        int size = 0;
        for (Map.Entry<Integer, List<WorkoutSet>> set : exerciseSets.entrySet()) {
            if (set.getKey() == id) {
                List<WorkoutSet> sets = set.getValue();
                size = sets.size();

            }
        }
        System.out.println("Exercise id: "+id+" size: "+ size);
        return size;
    }

    public String getData() {
        StringBuilder result = new StringBuilder();
        result.append(this.title).append("\n");
        int i = 0;
        for (Map.Entry<Integer, List<WorkoutSet>> set : exerciseSets.entrySet()) {
            List<WorkoutSet> sets = set.getValue();
                for(WorkoutSet set1 : sets) {
                    result.append(set1. getExercise().getName() + set1.getReps()).append(" ").append(set1.getWeight()).append(" ").append(set1.getRir()).append("\n");

            }
        }
        return result.toString();
    }

    public void deleteExercise(int exerciseId) {
        exerciseSets.remove(exerciseId);
    }

    public HashMap<Integer, List<WorkoutSet>> getExerciseSets() {
        return exerciseSets;
    }

    public int getTotalWorkoutHeight() {
        return totalWorkoutHeight;
    }

    public void setTotalWorkoutHeight(int totalWorkoutHeight) {
        this.totalWorkoutHeight = totalWorkoutHeight;
    }
}



