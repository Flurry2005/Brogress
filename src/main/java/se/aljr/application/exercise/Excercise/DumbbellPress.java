package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;

public class DumbbellPress extends Exercise {
    public DumbbellPress() {
        name = "Dumbbell Press";
        info = "A compound pressing exercise focusing on the chest, anterior deltoid, and triceps.";
        musclesUsed.add(new Chest());
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new Triceps());
    }
}
