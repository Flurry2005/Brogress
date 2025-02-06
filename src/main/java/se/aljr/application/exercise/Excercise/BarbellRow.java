package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;



public class BarbellRow extends Exercise {
    public BarbellRow() {
        name = "Barbell Row";
        info = "A barbell-based pulling exercise that focuses on lats, traps, and posterior deltoids, with lower back stability.";
        musclesUsed.add(new Lats());
        musclesUsed.add(new Traps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new LowerBack());
    }
}
