package se.aljr.application.programplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkoutData {
    private String name = "Untitled Workout";
    private HashMap<Integer, List<WorkoutSet>> exerciseSets = new HashMap<>();

    public String getName() {
        return this.name;
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

    public void deleteSet(int exerciseId, int setNumber) {

        List<WorkoutSet> sets = exerciseSets.get(exerciseId);
        sets.removeIf(set -> set.getNumber() == setNumber);

        int newSetId = 1;
        for (WorkoutSet s : sets) {
            s.setNumber(newSetId++);

            if (sets.isEmpty()) {
                exerciseSets.remove(exerciseId);
            }

        }
    }

    public int getSetSize (int id) {
        int size = 0;
        for (Map.Entry<Integer, List<WorkoutSet>> set : exerciseSets.entrySet()) {
            if (set.getKey() == id) {
                List<WorkoutSet> sets = set.getValue();
                size = sets.size();

            }
        }
        return size;
    }

    public String getData() {
        StringBuilder result = new StringBuilder();
        result.append(this.name).append("\n");

        for (Map.Entry<Integer, List<WorkoutSet>> set : exerciseSets.entrySet()) {
            result.append(set.getValue().get(0).exercise + "\n");
            set.getValue().forEach(s -> result.append(s.toString()));

        }
        return result.toString();
    }
}

