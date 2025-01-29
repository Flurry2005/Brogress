package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class DumbbellRow extends Exercise {
    public DumbbellRow() {
        name = "Dumbbell Row";
        info = "A single-arm pulling exercise targeting the lats, biceps, and traps, with posterior deltoid engagement.";
        musclesUsed.add(new Lats());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new Traps());
        musclesUsed.add(new LowerBack());
    }
}
