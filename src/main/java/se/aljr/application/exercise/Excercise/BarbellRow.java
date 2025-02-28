package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;
import se.aljr.application.exercise.Program.Exercises;

import javax.swing.*;


public class BarbellRow extends Exercise {
    public BarbellRow() {
        name = "Barbell Row";

        info = "The barbell row is a strength-training exercise that targets the upper and middle back, along with the biceps and core. It helps build muscle mass, improve posture, and enhance pulling strength, making it beneficial for overall upper-body development and athletic performance. Often used in bodybuilding, powerlifting, and general fitness routines, it is a staple for developing a strong and well-balanced back.";

        form = "To perform a barbell row, stand with feet shoulder-width apart, hinge at the hips, and keep your back straight. Grip the barbell with an overhand grip, pull it toward your lower ribcage while squeezing your shoulder blades, then lower it in a controlled manner. Keep your core engaged and maintain proper posture throughout the movement.";

        musclesUsed.add(new Lats());
        musclesUsed.add(new Traps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new LowerBack());


        imageIcon = new ImageIcon(ResourcePath.getResourcePath() +"ExerciseImages\\BarbellRow.png");
    }
}
