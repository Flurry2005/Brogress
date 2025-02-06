package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;

public class InclineBenchPress extends Exercise {
    public InclineBenchPress() {
        name = "Incline Bench Press";

        info = "The incline bench press is a compound pressing exercise that primarily targets the upper chest (pectoralis major), with secondary activation of the anterior deltoids and triceps. \n" +
                "This movement focuses on building the upper portion of the chest, contributing to overall upper-body development.";

        form = "Good Form and Tips\n" +
                "Tuck Elbows at 45 Degrees: Protects the shoulders and enhances chest engagement.\n" +
                "Maintain a Neutral Spine: Keep your back flat on the bench with a slight natural arch.\n" +
                "Control the Movement: Lower the bar slowly and press it up evenly with power.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Flared Elbows: Increases strain on the shoulders and can lead to injury.\n" +
                "Arching the Lower Back: Compromises form and may cause lower back pain.\n" +
                "Uneven Dumbbell/Bar Movement: Can lead to imbalances or injuries.";

        link = "https://musclewiki.com/barbell/male/anterior-deltoid/barbell-incline-bench-press";

        picture = "https://weighttraining.guide/exercises/smith-machine-incline-bench-press/";

        musclesUsed.add(new Chest());
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new Triceps());
    }
}
