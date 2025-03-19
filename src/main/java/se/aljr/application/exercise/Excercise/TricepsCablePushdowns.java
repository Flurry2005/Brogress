package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.Triceps;

import javax.swing.*;

public class TricepsCablePushdowns extends Exercise {
    public TricepsCablePushdowns() {
        name = "Cable Pushdowns";

        info = "The cable pushdown is an isolation exercise that primarily targets the triceps, helping to build strength, size, and definition in the arms. It is a staple in triceps-focused workouts due to its effectiveness and ease of use.";

        form = "To perform a cable pushdown, stand in front of a cable machine with a straight bar or rope attachment. Grip the handle with palms facing down, keep your elbows close to your body, and push the handle down until your arms are fully extended. Slowly return to the starting position while maintaining control and keeping tension on the triceps.";

        imageIcon = new ImageIcon(ResourcePath.getResourcePath("ExerciseImages/CablePushdown.gif"));
        imageIconPath = "ExerciseImages/CablePushdown.gif";
        musclesUsed.add(new Triceps());
    }
}
