package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;

public class CableRow extends Exercise {
    public CableRow() {
        name = "Cable Row";

        info = "The cable row is a seated pulling exercise that targets the lats (latissimus dorsi), biceps, and traps (trapezius). \n" +
                "It also engages the posterior deltoids and lower back to help stabilize the movement.";

        form = "Good Form and Tips\n" +
                "Maintain an Upright Posture: Keep your back straight and avoid excessive leaning.\n" +
                "Engage the Core: Helps stabilize the lower back throughout the movement.\n" +
                "Squeeze the Shoulder Blades: Focus on pulling with the lats and avoid relying on your arms.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Leaning Too Far Back: Reduces muscle activation and strains the lower back.\n" +
                "Using Too Much Arm Movement: Limits engagement of the back muscles.\n" +
                "Shrugging the Shoulders: Can overwork the traps and reduce lat involvement.";

        link = "https://musclewiki.com/machine/male/biceps/machine-seated-cable-row";

        picture = "https://weighttraining.guide/exercises/straight-back-seated-cable-row-with-straight-bar/";

        musclesUsed.add(new Lats());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new Traps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new LowerBack());

        imageIcon = new ImageIcon(ResourcePath.getResourcePath() + "ExerciseImages\\CableRow.gif");
    }

}
