package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class PullUp extends Exercise {
    public PullUp() {
        name = "PullUp";
        info = "A compound bodyweight exercise primarily targeting the lats, biceps, and posterior deltoid.";
        musclesUsed.add(new Lats());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new Forearms());
        musclesUsed.add(new Traps());
    }
}
