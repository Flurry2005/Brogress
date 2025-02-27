package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;

public class Lunge extends Exercise {
    public Lunge() {
        name = "Lunge";

        info = "The lunge is a compound lower-body exercise that targets the quadriceps, hamstrings, glutes, and core. It improves leg strength, balance, and overall lower-body stability, making it a versatile movement for strength and functional training.";

        form = "To perform a lunge, step forward with one leg and lower your hips until both knees form a 90-degree angle. Push through your front heel to return to the starting position, then repeat on the other leg. Keep your core engaged and maintain an upright posture throughout the movement.";

        imageIcon = new ImageIcon(ResourcePath.getResourcePath()+"ExerciseImages\\Lunge.png");

        musclesUsed.add(new Glutes());
        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Calves());
        musclesUsed.add(new Abs());
    }
}
