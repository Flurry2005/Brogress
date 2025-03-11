package se.aljr.application.programplanner;

import se.aljr.application.exercise.Excercise.Exercise;

import javax.swing.*;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;


public class Workout extends JPanel {

    @Serial
    private  static final long serialVersionUID = 1303335218637308425L;

    private boolean isDefault = false;

    private String workoutInfo;

    private final Map<Integer, JPanel> exercisePanels = new HashMap<>();

    private Map<Exercise, Integer> idToExercise = new HashMap<>();

    private Map<Integer, JPanel> setPanels = new HashMap<>();
    private Map<Integer, Integer> exerciseSetCount = new HashMap<>();


    public WorkoutData currentWorkout = new WorkoutData();

    public WorkoutData getWorkoutData(){
        return currentWorkout;
    }

    public Map<Integer, JPanel> getExercisePanels(){
        return exercisePanels;
    }


    public Map<Integer, Integer> getExerciseSetCount() {
        return exerciseSetCount;
    }


    public Map<Integer, JPanel> getSetPanels() {
        return setPanels;
    }

    public Exercise getIdToExercise(int i) {
        Exercise e = null;
        for(Map.Entry<Exercise, Integer> exercise : idToExercise.entrySet()){
            if(exercise.getValue()==i){
                e = exercise.getKey();
            }
        }
        return e;
    }

    public Map<Exercise, Integer> addIdToExercise(){
        return idToExercise;
    }

    public void setDefault(boolean daBoolean) {
        isDefault = daBoolean;
    }

    public boolean isWorkoutDefault() {
        return isDefault;
    }

    public void setWorkoutInfo (String text) {
        workoutInfo = text;
    }

    public String getWorkoutInfo () {
        return workoutInfo;
    }


}
