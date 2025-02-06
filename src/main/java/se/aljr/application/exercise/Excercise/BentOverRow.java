package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;


public class BentOverRow extends Exercise {
    public BentOverRow() {
        name = "Bent Over Row";
        info = "A pulling exercise targeting the lats, traps, and posterior deltoid, with lower back engagement.";
        musclesUsed.add(new Lats());
        musclesUsed.add(new Traps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new LowerBack());
    }
}
