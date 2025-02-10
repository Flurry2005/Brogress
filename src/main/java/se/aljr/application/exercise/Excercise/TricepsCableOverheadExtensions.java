package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Triceps;

public class TricepsCableOverheadExtensions extends Exercise {
    public TricepsCableOverheadExtensions() {
        name = "Overhead-Extensions (Cable)";

        info = "The overhead triceps extension (cable) is an isolation exercise targeting the triceps. \n" +
                "It involves extending the arms overhead against resistance, effectively building triceps strength and size while improving upper arm definition.";

        form = "Good Form and Tips\n" +
                "Keep Elbows Close to Your Head: Helps isolate the triceps for maximum activation.\n" +
                "Engage the Core: Maintains proper posture and prevents lower back arching.\n" +
                "Control the Movement: Perform slow and steady reps to ensure full muscle engagement.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Flaring Elbows Out: Reduces triceps engagement and can strain the shoulders.\n" +
                "Arching the Lower Back: Puts unnecessary stress on the spine.\n" +
                "Using Momentum: Reduces control and limits muscle activation.";

        link = "https://musclewiki.com/cables/male/triceps/cable-rope-overhead-tricep-extension";

        picture = "https://weighttraining.guide/exercises/standing-dumbbell-overhead-triceps-extension/";

        musclesUsed.add(new Triceps());
    }
}
