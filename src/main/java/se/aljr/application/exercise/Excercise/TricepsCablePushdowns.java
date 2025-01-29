package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Triceps;

public class TricepsCablePushdowns extends Exercise {
    public TricepsCablePushdowns() {
        name = "Cable-Pushdowns";
        info = "An isolation exercise focusing on the triceps using a cable machine.";
        musclesUsed.add(new Triceps());
    }
}
