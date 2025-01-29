package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class StepUp extends Exercise {
    public StepUp() {
        name = "StepUp";
        info = "A unilateral lower-body exercise emphasizing the quadriceps, glutes, and hamstrings, with core stabilization.";
        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Calves());
        musclesUsed.add(new Abs());
    }
}
