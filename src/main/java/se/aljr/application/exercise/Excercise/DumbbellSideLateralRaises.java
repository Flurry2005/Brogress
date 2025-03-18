package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.LateralDeltoid;
import se.aljr.application.exercise.Muscle.Traps;

import javax.swing.*;

public class DumbbellSideLateralRaises extends Exercise {
    public DumbbellSideLateralRaises() {
        name = "Dumbbell Side Lateral Raises";

        info = "The dumbbell side lateral raise is an isolation exercise that targets the lateral deltoids, helping to build shoulder width and definition. It enhances shoulder strength and aesthetics, making it a staple in many workout routines.";

        form = "To perform a dumbbell side lateral raise, stand with a dumbbell in each hand, arms at your sides. With a slight bend in your elbows, lift the dumbbells out to the sides until they reach shoulder height, then slowly lower them back down. Maintain controlled movement and avoid using momentum.";

        imageIcon = new ImageIcon(ResourcePath.getResourcePath("ExerciseImages/DumbbellSideLateralRaise.gif"));
        imageIconPath = "ExerciseImages/DumbbellSideLateralRaise.gif";
        musclesUsed.add(new LateralDeltoid());
        musclesUsed.add(new Traps());
        musclesUsed.add(new AnteriorDeltoid());
    }
}
