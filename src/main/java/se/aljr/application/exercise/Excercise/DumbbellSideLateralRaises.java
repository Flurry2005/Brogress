package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.LateralDeltoid;
import se.aljr.application.exercise.Muscle.Traps;

public class DumbbellSideLateralRaises extends Exercise {
    public DumbbellSideLateralRaises() {
        name = "Lateral Raises";
        info = "An isolation exercise for the lateral deltoids, with secondary traps and anterior deltoid engagement.";
        musclesUsed.add(new LateralDeltoid());
        musclesUsed.add(new Traps());
        musclesUsed.add(new AnteriorDeltoid());
    }
}
