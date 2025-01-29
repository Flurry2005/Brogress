package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.LateralDeltoid;
import se.aljr.application.exercise.Muscle.Traps;

public class CableSideLateralRaises extends Exercise {
    public CableSideLateralRaises() {
        name = "Lateral Raises (Cable)";
        info = "A cable-based isolation movement for the lateral deltoids, with traps and anterior deltoid involvement.";
        musclesUsed.add(new LateralDeltoid());
        musclesUsed.add(new Traps());
        musclesUsed.add(new AnteriorDeltoid());
    }
}
