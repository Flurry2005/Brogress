package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;


public class BentOverRow extends Exercise {
    public BentOverRow() {
        name = "Dumbbell Bent Over Row";

        info = "The dumbbell bent-over row is a strength exercise that targets the upper and middle back, as well as the biceps and core. It helps improve posture, build muscle mass, and enhance pulling strength, making it a valuable addition to any workout routine.";

        form = "To perform a dumbbell bent-over row, hold a dumbbell in each hand, hinge at the hips, and keep your back straight. Pull the dumbbells toward your torso, squeezing your shoulder blades together, then lower them back down in a controlled manner. Keep your core engaged and maintain proper posture throughout the movement.";

        musclesUsed.add(new Lats());
        musclesUsed.add(new Traps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new LowerBack());

        imageIcon = new ImageIcon(ResourcePath.getResourcePath() +"ExerciseImages\\DumbbellRow.gif");
    }
}
