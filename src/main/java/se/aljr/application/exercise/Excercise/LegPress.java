package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Calves;
import se.aljr.application.exercise.Muscle.Glutes;
import se.aljr.application.exercise.Muscle.Hamstrings;
import se.aljr.application.exercise.Muscle.Quadriceps;

public class LegPress extends Exercise {
    public LegPress() {
        name = "Leg Press";

        info = "The leg press is a machine-based lower-body exercise that primarily targets the quadriceps, glutes, and hamstrings. The calves also assist during the movement. \n" +
                "It is ideal for building lower-body strength while reducing spinal stress compared to free-weight exercises.";

        form = "Good Form and Tips\n" +
                "Keep Your Back and Hips Pressed Against the Seat: Prevents lower back injuries.\n" +
                "Don’t Lock Out the Knees: Maintain a slight bend at the top to protect the joints.\n" +
                "Controlled Motion: Lower and push with control to maximize muscle activation.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Lifting the Hips Off the Seat: Increases lower back strain and risks injury.\n" +
                "Locking the Knees at the Top: Puts excess pressure on the knee joints.\n" +
                "Excessive Range of Motion: Bringing the knees too close can strain the knees and lower back.";

        link = "https://musclewiki.com/machine/male/glutes/machine-single-leg-leg-press";

        picture = "https://weighttraining.guide/exercises/incline-leg-press/";

        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Calves());
    }
}
