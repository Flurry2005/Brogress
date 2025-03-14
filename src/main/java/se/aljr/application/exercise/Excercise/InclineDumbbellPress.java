package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;

import javax.swing.*;

public class InclineDumbbellPress extends Exercise {
    public InclineDumbbellPress() {
        name = "Incline Dumbbell Press";

        info = "The incline dumbbell press is a compound exercise that primarily targets the upper chest, while also engaging the shoulders and triceps. It helps build muscle mass, strength, and improves chest definition.";

        form = "To perform an incline dumbbell press, lie on an incline bench (set at 30-45 degrees) with a dumbbell in each hand. Start with your elbows bent at a 90-degree angle, then press the dumbbells upward until your arms are fully extended. Slowly lower them back down with control while keeping your core engaged.";

        musclesUsed.add(new Chest());
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new Triceps());

        imageIcon = new ImageIcon(ResourcePath.getResourcePath("ExerciseImages/InclineDumbbellPress.gif"));
    }
}
