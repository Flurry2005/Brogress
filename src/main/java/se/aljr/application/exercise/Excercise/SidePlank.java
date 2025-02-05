package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Abs;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Glutes;
import se.aljr.application.exercise.Muscle.Obliques;

public class SidePlank extends Exercise {
    public SidePlank() {
        name = "Side Plank";

        info = "The side plank is an isometric core exercise that primarily targets the obliques and abs, with secondary engagement of the glutes and anterior deltoids to support shoulder and hip stability. \n" +
                "This exercise improves core strength, balance, and spinal alignment.";

        form = "Maintain a Straight Line: Keep your body aligned from head to heels.\n" +
                "Engage the Core and Glutes: Helps maintain proper alignment and balance.\n" +
                "Breathe Steadily: Maintain steady breathing to hold the position longer and maintain form.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Sagging Hips: Reduces core activation and places unnecessary strain on the lower back.\n" +
                "Leaning Forward or Backward: Misaligns the body, reducing effectiveness.\n" +
                "Holding Breath: Can lead to tension and improper form.";

        link = "https://musclewiki.com/bodyweight/male/obliques/hand-side-plank";

        picture = "https://weighttraining.guide/exercises/high-side-plank/";

        musclesUsed.add(new Obliques());
        musclesUsed.add(new Abs());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new AnteriorDeltoid());
    }
}
