package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.LateralDeltoid;
import se.aljr.application.exercise.Muscle.Traps;

import javax.swing.*;

public class CableSideLateralRaises extends Exercise {
    public CableSideLateralRaises() {
        name = "Cable Side Lateral Raises";

        info = "The cable side lateral raise is an isolation exercise that targets the lateral deltoids, helping to build shoulder width and definition. It provides constant tension throughout the movement, making it an effective alternative to dumbbell lateral raises.";

        form = "To perform a cable side lateral raise, stand next to a cable machine and grip the handle with the hand furthest from the machine. With a slight bend in your elbow, lift your arm to the side until it reaches shoulder height, then slowly lower it back down. Keep your core engaged and maintain controlled movement throughout.";

        musclesUsed.add(new LateralDeltoid());
        musclesUsed.add(new Traps());
        musclesUsed.add(new AnteriorDeltoid());

        imageIcon = new ImageIcon(ResourcePath.getResourcePath("ExerciseImages/CableSideLateralRaises.gif"));
    }
}
