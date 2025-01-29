package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Abs;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Glutes;
import se.aljr.application.exercise.Muscle.Obliques;

public class SidePlank extends Exercise {
    public SidePlank() {
        name = "Side Plank";
        info = "An isometric core exercise focusing on obliques, abs, and glutes, with shoulder stability.";
        musclesUsed.add(new Obliques());
        musclesUsed.add(new Abs());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new AnteriorDeltoid());
    }
}
