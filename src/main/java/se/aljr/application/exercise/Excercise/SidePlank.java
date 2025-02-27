package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Abs;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Glutes;
import se.aljr.application.exercise.Muscle.Obliques;

public class SidePlank extends Exercise {
    public SidePlank() {
        name = "Side Plank";

        info = "The side plank is a core-strengthening exercise that primarily targets the obliques while also engaging the shoulders, glutes, and overall core for stability. It helps improve balance, posture, and core endurance.";

        form = "To perform a side plank, lie on your side with your legs stacked and your forearm directly under your shoulder. Lift your hips off the ground, keeping your body in a straight line from head to feet. Hold the position while engaging your core and maintaining steady breathing. Repeat on the other side.";

        musclesUsed.add(new Obliques());
        musclesUsed.add(new Abs());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new AnteriorDeltoid());
    }
}
