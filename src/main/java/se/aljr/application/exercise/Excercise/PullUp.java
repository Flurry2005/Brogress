package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class PullUp extends Exercise {
    public PullUp() {
        name = "PullUp";

        info = "The pull-up is a compound bodyweight exercise that primarily targets the lats (latissimus dorsi), with secondary involvement from the biceps, posterior deltoid, forearms, and traps. \n" +
                "It is a key exercise for building upper body strength and width.";

        form = "Good Form and Tips\n" +
                "Engage the Core: Keep your body stable and prevent excessive swinging.\n" +
                "Full Range of Motion: Lower until arms are extended and pull up until chin clears the bar.\n" +
                "Retract the Shoulder Blades: Initiate the pull by engaging the lats and back muscles, not just the arms.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Using Momentum (Kipping): Swinging the body reduces muscle engagement and increases injury risk.\n" +
                "Incomplete Range of Motion: Not fully lowering or pulling up reduces effectiveness.\n" +
                "Overgripping or Tensing the Wrists: Can strain the wrists and forearms.";

        link = "https://musclewiki.com/bodyweight/male/traps-middle/pull-ups";

        picture = "https://weighttraining.guide/exercises/pull-up/";

        musclesUsed.add(new Lats());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new Forearms());
        musclesUsed.add(new Traps());
    }
}
