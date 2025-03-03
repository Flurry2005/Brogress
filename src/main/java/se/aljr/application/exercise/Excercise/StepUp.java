package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;

public class StepUp extends Exercise {
    public StepUp() {
        name = "Step-Up";

        info = "The step-up is a compound lower-body exercise that primarily targets the quadriceps, hamstrings, and glutes while also improving balance and coordination. It is a functional movement that enhances lower-body strength and stability.";

        form = "To perform a step-up, stand in front of a sturdy bench or box. Step onto it with one foot, driving through your heel to lift your body up until your other foot meets the platform. Step back down in a controlled manner and repeat, alternating legs or focusing on one side before switching.";
        imageIcon = new ImageIcon(ResourcePath.getResourcePath()+"ExerciseImages\\StepUp.gif");
        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Calves());
        musclesUsed.add(new Abs());
    }
}
