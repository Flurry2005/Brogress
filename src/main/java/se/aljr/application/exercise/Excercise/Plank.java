package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Abs;
import se.aljr.application.exercise.Muscle.LowerBack;
import se.aljr.application.exercise.Muscle.Obliques;

public class Plank extends Exercise {
    public Plank() {
        name = "Plank";
        info = "An isometric core exercise focusing on abs, obliques, and lower back stabilization.";
        musclesUsed.add(new Abs());
        musclesUsed.add(new Obliques());
        musclesUsed.add(new LowerBack());
    }
}
