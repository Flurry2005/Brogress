package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;

public class InclineDumbbellPress extends Exercise {
    public InclineDumbbellPress() {
        name = "Incline Dumbbell Press";

        info = "The incline dumbbell press is a pressing movement that focuses on the upper chest (pectoralis major), with secondary involvement of the anterior deltoids and triceps. \n" +
                "It helps in building upper chest strength and muscle mass while improving shoulder stability.";

        form = "Good Form and Tips\n" +
                "Tuck Elbows Slightly (45 Degrees): Helps protect the shoulders and increase chest activation.\n" +
                "Engage the Core: Prevents lower back from overextending during the lift.\n" +
                "Control the Dumbbells: Press evenly and lower the dumbbells slowly to maximize muscle activation.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Flared Elbows: Puts unnecessary stress on the shoulders.\n" +
                "Overarching the Lower Back: Can cause lower back strain.\n" +
                "Uneven Pressing: Lifting one dumbbell faster than the other reduces effectiveness.";

        link = "https://musclewiki.com/dumbbells/male/anterior-deltoid/dumbbell-incline-bench-press";

        picture = "https://weighttraining.guide/exercises/incline-dumbbell-bench-press/";

        musclesUsed.add(new Chest());
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new Triceps());
    }
}
