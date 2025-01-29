package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class Lunge extends Exercise {
    public Lunge() {
        name = "Lunge";
        info = "A unilateral lower-body exercise targeting the glutes, quadriceps, and hamstrings, with core involvement.";
        musclesUsed.add(new Glutes());
        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Calves());
        musclesUsed.add(new Abs());
    }
}
