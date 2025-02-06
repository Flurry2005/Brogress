package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class StepUp extends Exercise {
    public StepUp() {
        name = "StepUp";

        info = "The step-up is a unilateral lower-body exercise that primarily targets the quadriceps, glutes, and hamstrings. \n" +
                "It also involves the calves and core for balance and stabilization, making it effective for developing functional lower-body strength.";

        form = "Good Form and Tips\n" +
                "Drive Through the Heel of the Lead Leg: Engages the glutes and quads effectively.\n" +
                "Maintain an Upright Posture: Keep your chest up and avoid leaning forward.\n" +
                "Engage the Core: Helps maintain balance and supports the spine during the movement.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Pushing Off with the Back Leg: Reduces workload on the leading leg, limiting effectiveness.\n" +
                "Leaning Forward: Can cause lower back strain and imbalance.\n" +
                "Inconsistent Step Height: Stepping too low or too high can decrease the effectiveness.";

        link = "https://musclewiki.com/dumbbells/male/glutes/dumbbell-step-up";

        picture = "https://weighttraining.guide/exercises/dumbbell-step-up/";

        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Calves());
        musclesUsed.add(new Abs());
    }
}
