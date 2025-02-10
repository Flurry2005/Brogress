package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Triceps;

public class TricepsCablePushdowns extends Exercise {
    public TricepsCablePushdowns() {
        name = "Cable-Pushdowns";

        info = "The cable triceps pushdown is an isolation exercise that primarily targets the triceps. \n" +
                "It is performed using a cable machine and is effective for building upper arm strength and size.";

        form = "Good Form and Tips\n" +
                "Keep Elbows Tucked In: Helps isolate the triceps for maximum contraction.\n" +
                "Control the Movement: Perform slow, controlled reps, avoiding fast or jerky motions.\n" +
                "Maintain a Straight Posture: Engage the core to prevent leaning forward or backward.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Flaring Elbows Out: Reduces triceps engagement and increases shoulder involvement.\n" +
                "Using Momentum: Limits control and reduces muscle activation.\n" +
                "Partial Range of Motion: Shortens the movement, reducing effectiveness.";

        link = "https://musclewiki.com/cables/male/triceps/cable-rope-pushdown";

        picture = "https://weighttraining.guide/exercises/triceps-push-down/";

        musclesUsed.add(new Triceps());
    }
}
