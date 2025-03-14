package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;

import javax.swing.*;

public class DumbbellPress extends Exercise {
    public DumbbellPress() {
        name = "Dumbbell Chest Press";

        info = "The dumbbell chest press is a compound exercise that targets the chest, shoulders, and triceps. It helps build upper body strength, muscle mass, and stability while allowing for a greater range of motion compared to the barbell bench press.";

        form = "To perform a dumbbell chest press, lie on a bench with a dumbbell in each hand, elbows bent at a 90-degree angle. Press the dumbbells upward until your arms are fully extended, then slowly lower them back to the starting position. Keep your core engaged and maintain control throughout the movement.";

        imageIcon = new ImageIcon(ResourcePath.getResourcePath("ExerciseImages/DumbbellPress.gif"));
        musclesUsed.add(new Chest());
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new Triceps());
    }
}
