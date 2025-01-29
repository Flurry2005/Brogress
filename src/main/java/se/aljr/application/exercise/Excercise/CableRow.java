package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class CableRow extends Exercise {
    public CableRow() {
        name = "Cable Row";
        info = "A seated pulling exercise targeting the lats, biceps, and traps, with posterior deltoid and lower back engagement.";
        musclesUsed.add(new Lats());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new Traps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new LowerBack());
    }
}
