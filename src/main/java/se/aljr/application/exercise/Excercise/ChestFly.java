package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Biceps;
import se.aljr.application.exercise.Muscle.Chest;

import javax.swing.*;

public class ChestFly extends Exercise {
    public ChestFly() {
        name = "Cable Chest Fly";

        info = "The cable chest fly is an isolation exercise that targets the chest muscles, particularly the pectorals, while also engaging the shoulders and triceps. It helps improve muscle definition, strength, and control by providing constant tension throughout the movement.";

        form = "To perform a cable chest fly, stand between two cable machines with handles in each hand, arms slightly bent. Bring your hands together in front of your chest in a wide arc, squeezing your chest muscles, then slowly return to the starting position with control. Keep your core engaged and maintain a slight bend in your elbows throughout.";

        musclesUsed.add(new Chest());
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new Biceps());

        imageIcon = new ImageIcon(ResourcePath.getResourcePath() +"ExerciseImages\\CableChestFly.gif");
    }
}
