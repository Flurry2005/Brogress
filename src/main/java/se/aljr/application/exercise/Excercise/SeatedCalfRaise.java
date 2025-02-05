package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Calves;
import se.aljr.application.exercise.Muscle.Hamstrings;

public class SeatedCalfRaise extends Exercise {
    public SeatedCalfRaise() {
        name = "Seated Calf Raise";

        info = "The seated calf raise is an isolation exercise focusing on the calves (gastrocnemius and soleus), with some secondary involvement of the hamstrings. \n" +
                "It is effective for building calf strength, size, and endurance.";

        form = "Good Form and Tips\n" +
                "Full Range of Motion: Lower until you feel a full stretch and rise until calves are fully contracted.\n" +
                "Control the Movement: Perform slow, steady reps to ensure maximum calf activation.\n" +
                "Keep the Balls of Your Feet on the Platform: Helps maintain balance and isolate the calves.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Bouncing the Weight: Reduces muscle engagement and can lead to injury.\n" +
                "Limited Range of Motion: Not fully stretching and contracting the calves reduces effectiveness.\n" +
                "Relying on Momentum: Swinging or jerky motions reduce controlled muscle activation.";

        link = "https://musclewiki.com/machine/male/calves/machine-seated-calf-raises";

        picture = "https://weighttraining.guide/exercises/seated-calf-raise/";

        musclesUsed.add(new Calves());
        musclesUsed.add(new Hamstrings());
    }
}
