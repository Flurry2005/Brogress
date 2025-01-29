package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Calves;
import se.aljr.application.exercise.Muscle.Hamstrings;

public class SeatedCalfRaise extends Exercise {
    public SeatedCalfRaise() {
        name = "Seated Calf Raise";
        info = "An isolation exercise focusing on the calves and hamstrings.";
        musclesUsed.add(new Calves());
        musclesUsed.add(new Hamstrings());
    }
}
