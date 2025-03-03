package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;

public class CableRow extends Exercise {
    public CableRow() {
        name = "Cable Row";

        info = "The cable row is a resistance exercise that primarily targets the upper and middle back, along with the biceps and core. It helps build strength, improve posture, and enhance muscle definition, making it a staple in many strength-training routines.";

        form = "To perform a cable row, sit at a cable machine with feet firmly planted, grip the handle, and keep your back straight. Pull the handle toward your torso while squeezing your shoulder blades together, then slowly extend your arms back to the starting position. Maintain controlled movements and engage your core throughout.";

        musclesUsed.add(new Lats());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new Traps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new LowerBack());

        imageIcon = new ImageIcon(ResourcePath.getResourcePath() + "ExerciseImages\\CableRow.gif");
    }

}
