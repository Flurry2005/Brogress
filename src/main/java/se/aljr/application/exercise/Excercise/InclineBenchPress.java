package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;

public class InclineBenchPress extends Exercise {
    public InclineBenchPress() {
        name = "Incline Bench Press";
        info = "A pressing movement targeting the upper chest, anterior deltoid, and triceps.";
        musclesUsed.add(new Chest());
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new Triceps());
    }
}
