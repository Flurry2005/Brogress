package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;


public class BarbellRow extends Exercise {
    public BarbellRow() {
        name = "Barbell Row";

        info = "A barbell-based pulling exercise that focuses on lats, traps, and posterior deltoids, with lower back stability.";

        form = "Good Form and Tips:\n" +
                "Maintain a Neutral Spine: Keep your back straight and chest up.\n" +
                "Engage the Core: Tighten your abs to stabilize and protect the lower back.\n" +
                "Controlled Movements: Lift the bar slowly and keep it close to your body.";

        mistakes = "Common Mistakes to Avoid:\n" +
                "Rounding the Back: Can lead to serious lower back injuries.\n" +
                "Using Too Much Momentum: Reduces muscle activation and increases injury risk.\n" +
                "Neglecting Core Engagement: Leads to excess stress on the lower back.";

        link = "https://musclewiki.com/barbell/male/shoulders/barbell-upright-row";

        picture = "https://weighttraining.guide/exercises/barbell-wide-grip-upright-row/";

        musclesUsed.add(new Lats());
        musclesUsed.add(new Traps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new LowerBack());

        imageIcon = new ImageIcon(ResourcePath.getResourcePath() +"ExerciseImages\\BarbellRow.gif");
    }
}
