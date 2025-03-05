package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.Abs;
import se.aljr.application.exercise.Muscle.LowerBack;
import se.aljr.application.exercise.Muscle.Obliques;

import javax.swing.*;
import java.awt.*;

public class Plank extends Exercise {
    public Plank() {
        name = "Plank";

        info = "The plank is a core-strengthening exercise that engages the abdominals, lower back, shoulders, and glutes. It helps improve core stability, posture, and overall body control.";

        form = "To perform a plank, place your forearms on the ground with elbows aligned under your shoulders and extend your legs straight behind you. Keep your body in a straight line from head to heels, engage your core, and hold the position for the desired duration while maintaining steady breathing.";

        musclesUsed.add(new Abs());
        musclesUsed.add(new Obliques());
        musclesUsed.add(new LowerBack());
    }
}
