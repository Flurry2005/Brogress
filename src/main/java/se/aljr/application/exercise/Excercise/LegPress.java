package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.Calves;
import se.aljr.application.exercise.Muscle.Glutes;
import se.aljr.application.exercise.Muscle.Hamstrings;
import se.aljr.application.exercise.Muscle.Quadriceps;

import javax.swing.*;

public class LegPress extends Exercise {
    public LegPress() {
        name = "Leg Press";

        info = "The leg press is a compound lower-body exercise that primarily targets the quadriceps, hamstrings, and glutes. It helps build leg strength, muscle mass, and endurance while reducing spinal load compared to squats.";

        form = "To perform a leg press, sit on the leg press machine with your feet shoulder-width apart on the platform. Push the platform away by extending your legs, then slowly lower it back down until your knees are at a 90-degree angle. Keep your back flat against the seat and maintain controlled movement throughout.";

        imageIcon = new ImageIcon(ResourcePath.getResourcePath()+"ExerciseImages\\LegPress.png");

        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Calves());
    }
}
