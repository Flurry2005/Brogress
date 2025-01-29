package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Biceps;
import se.aljr.application.exercise.Muscle.Chest;

public class ChestFly extends Exercise {
    public ChestFly() {
        name = "Chest Fly (Cable)";
        info = "An isolation exercise targeting the chest, anterior deltoid, and biceps.";
        musclesUsed.add(new Chest());
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new Biceps());
    }
}
