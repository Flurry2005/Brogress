package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.Triceps;

import javax.swing.*;

public class TricepsFrenchPress extends Exercise {
    public TricepsFrenchPress() {
        name = "Dumbbell French Press";

        info = "The dumbbell French press is an isolation exercise that primarily targets the triceps, helping to build arm strength and muscle definition. It emphasizes the long head of the triceps, contributing to overall arm development.";

        form = "To perform a dumbbell French press, sit, lie down or stand while holding a dumbbell with both hands, extending it overhead. Keep your elbows close to your head as you lower the dumbbell behind your head in a controlled motion. Press the dumbbell back up to the starting position, fully extending your arms while keeping tension on the triceps.";
        imageIcon = new ImageIcon(ResourcePath.getResourcePath("ExerciseImages/FrenchPress.gif"));
        imageIconPath = "ExerciseImages/FrenchPress.gif";
        musclesUsed.add(new Triceps());
    }
}
