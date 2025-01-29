package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Calves;
import se.aljr.application.exercise.Muscle.Glutes;
import se.aljr.application.exercise.Muscle.Hamstrings;
import se.aljr.application.exercise.Muscle.Quadriceps;

public class LegPress extends Exercise {
    public LegPress() {
        name = "Leg Press";
        info = "A machine-based lower-body exercise targeting the quadriceps, glutes, and hamstrings.";
        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Calves());
    }
}
