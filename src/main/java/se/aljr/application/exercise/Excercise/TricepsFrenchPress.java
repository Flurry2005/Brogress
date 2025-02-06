package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Triceps;

public class TricepsFrenchPress extends Exercise {
    public TricepsFrenchPress() {
        name = "French-Press";

        info = "The French press is a triceps-focused exercise performed overhead using a dumbbell or barbell. \n" +
                "It primarily targets the triceps, promoting arm strength and muscle growth.";

        form = "Good Form and Tips\n" +
                "Keep Elbows Close to Your Head: Helps isolate the triceps effectively.\n" +
                "Engage the Core: Prevents lower back from arching during the movement.\n" +
                "Control the Descent: Slowly lower the weight to ensure full range of motion and avoid injury.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Flaring the Elbows: Reduces triceps isolation and can strain the shoulders.\n" +
                "Arching the Lower Back: Increases risk of lower back injury.\n" +
                "Using Too Much Weight: Can lead to poor form and reduced muscle engagement.";

        link = "https://musclewiki.com/barbell/male/triceps/barbell-overhead-tricep-extension";

        picture = "https://weighttraining.guide/exercises/standing-overhead-barbell-triceps-extension/";

        musclesUsed.add(new Triceps());
    }
}
