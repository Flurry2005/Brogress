package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;
import java.util.ResourceBundle;

public class HipThrusts extends Exercise {

    public HipThrusts() {
        name = "Barbell Hip Thrusts";

        info = "The barbell hip thrust is a compound exercise that primarily targets the glutes, while also engaging the hamstrings and core. It is one of the most effective movements for building glute strength, power, and overall lower-body development.";

        form = "To perform a barbell hip thrust, sit with your upper back against a bench and place a barbell across your hips. Plant your feet shoulder-width apart, then drive through your heels to lift your hips until your body forms a straight line from shoulders to knees. Squeeze your glutes at the top, then lower back down with control.";
        imageIcon = new ImageIcon(ResourcePath.getResourcePath("ExerciseImages/HipThrust.gif"));
        musclesUsed.add(new Glutes());
        musclesUsed.add(new Hamstrings());
    }

}
