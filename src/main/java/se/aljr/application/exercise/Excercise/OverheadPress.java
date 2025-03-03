package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;

public class OverheadPress extends Exercise {
    public OverheadPress() {
        name = "Overhead Press";

        info = "The overhead press is a compound exercise that primarily targets the shoulders, while also engaging the triceps and upper chest. It helps build upper-body strength, stability, and muscle definition.";

        form = "To perform an overhead press, stand with your feet shoulder-width apart and grip the barbell at shoulder height with hands slightly wider than shoulder-width. Press the bar overhead until your arms are fully extended, then lower it back down with control. Keep your core engaged and maintain proper posture throughout the movement.";

        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new LateralDeltoid());
        musclesUsed.add(new Triceps());
        musclesUsed.add(new Traps());
        musclesUsed.add(new Abs());

        imageIcon = new ImageIcon(ResourcePath.getResourcePath() +"ExerciseImages\\OverheadPress.gif");
    }
}
