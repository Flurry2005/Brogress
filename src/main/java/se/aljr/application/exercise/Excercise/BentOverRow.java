package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;


public class BentOverRow extends Exercise {
    public BentOverRow() {
        name = "Bent Over Row";

        info = "The bent-over row is a pulling exercise primarily targeting the lats (latissimus dorsi), traps (trapezius), and posterior deltoids (rear shoulders). \n" +
                "It also engages the biceps as secondary muscles and stabilizes the lower back.";

        form = "Good Form and Tips\n" +
                "Maintain a Straight Back: Keep your spine neutral and chest slightly up.\n" +
                "Engage the Core: Helps stabilize the lower back and prevents injury.\n" +
                "Squeeze the Shoulder Blades: Focus on pulling with the back, not just the arms.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Rounding the Back: Can strain or injure the lower back.\n" +
                "Excessive Weight Use: Leads to poor form and reduced muscle engagement.\n" +
                "Pulling with the Arms Only: Limits activation of the back muscles.";

        link = "https://musclewiki.com/barbell/male/biceps/barbell-bent-over-row";

        picture = "https://weighttraining.guide/exercises/bent-over-barbell-row/";

        musclesUsed.add(new Lats());
        musclesUsed.add(new Traps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new LowerBack());
    }
}
