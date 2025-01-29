package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class OverheadPress extends Exercise {
    public OverheadPress() {
        name = "Overhead Press";
        info = "A compound pressing movement targeting the shoulders, triceps, and traps, with core stabilization.";
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new LateralDeltoid());
        musclesUsed.add(new Triceps());
        musclesUsed.add(new Traps());
        musclesUsed.add(new Abs());
    }
}
