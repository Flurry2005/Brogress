package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class OverheadPress extends Exercise {
    public OverheadPress() {
        name = "Overhead Press";

        info = "The overhead press is a compound pressing exercise targeting the anterior deltoids, lateral deltoids, triceps, and traps. \n" +
                "It also requires core stabilization to maintain balance and control, making it a full-body strength movement.";

        form = "Good Form and Tips\n" +
                "Maintain a Neutral Spine: Engage your core to prevent lower back arching.\n" +
                "Tuck Elbows Slightly Forward: Helps protect the shoulders and improves pressing strength.\n" +
                "Push the Bar in a Straight Path: Ensure the bar travels directly overhead for maximum efficiency.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Arching the Lower Back: Places stress on the spine and can lead to injury.\n" +
                "Flared Elbows: Increases shoulder strain and reduces pressing efficiency.\n" +
                "Pushing the Bar Forward: Leads to poor form and balance issues.";

        link = "https://musclewiki.com/barbell/male/shoulders/barbell-overhead-press";

        picture = "https://weighttraining.guide/exercises/standing-wide-grip-barbell-overhead-press/";

        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new LateralDeltoid());
        musclesUsed.add(new Triceps());
        musclesUsed.add(new Traps());
        musclesUsed.add(new Abs());
    }
}
