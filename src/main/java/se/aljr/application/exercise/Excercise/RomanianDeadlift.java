package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;

public class RomanianDeadlift extends Exercise {
    public RomanianDeadlift() {
        name = "Romanian Deadlift";

        info = "The Romanian deadlift (RDL) is a compound exercise that primarily targets the hamstrings and glutes while also engaging the lower back and core. It helps improve posterior chain strength, flexibility, and hip mobility.";

        form = "To perform a Romanian deadlift, stand with your feet hip-width apart, holding a barbell or dumbbells in front of your thighs. Hinge at the hips, lowering the weight while keeping your back straight and knees slightly bent. Lower until you feel a stretch in your hamstrings, then drive through your heels to return to the starting position. Maintain control throughout the movement.";
        imageIcon = new ImageIcon(ResourcePath.getResourcePath()+"ExerciseImages\\RDL.gif");
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new LowerBack());
        musclesUsed.add(new Forearms());
        musclesUsed.add(new Calves());
    }
}
