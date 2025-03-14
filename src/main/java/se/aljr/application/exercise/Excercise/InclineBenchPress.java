package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;

import javax.swing.*;
import java.util.ResourceBundle;

public class InclineBenchPress extends Exercise {
    public InclineBenchPress() {
        name = "Incline Bench Press";

        info = "The incline bench press is a compound exercise that primarily targets the upper chest, along with the shoulders and triceps. It helps develop upper body strength and improves overall chest definition.";

        form = "To perform an incline bench press, lie on an incline bench (set at 30-45 degrees) with your feet planted on the ground. Grip the bar slightly wider than shoulder-width, lower it to your upper chest, then press it back up until your arms are fully extended. Keep your core engaged and control the movement throughout.";

        imageIcon = new ImageIcon(ResourcePath.getResourcePath("ExerciseImages/InclineBenchPress.gif"));

        musclesUsed.add(new Chest());
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new Triceps());
    }
}
