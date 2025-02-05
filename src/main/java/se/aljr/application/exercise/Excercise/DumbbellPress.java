package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;

public class DumbbellPress extends Exercise {
    public DumbbellPress() {
        name = "Dumbbell Press";

        info = "The dumbbell press is a compound pressing exercise that targets the chest (pectoralis major), with secondary activation of the anterior deltoids and triceps.\n" +
                "It helps build upper-body strength and improve muscle balance.";

        form = "Good Form and Tips\n" +
                "Tuck Elbows Slightly (45 Degrees): Protects the shoulders and enhances chest activation.\n" +
                "Engage the Core: Maintain spinal alignment to avoid lower back arching.\n" +
                "Control the Movement: Press the dumbbells evenly and avoid rushing through reps.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Flared Elbows: Increases strain on the shoulders and reduces power output.\n" +
                "Arching the Lower Back: Can lead to lower back strain.\n" +
                "Uneven Dumbbell Movement: Reduces effectiveness and increases injury risk.";

        link = "https://musclewiki.com/dumbbells/male/anterior-deltoid/dumbbell-incline-bench-press";

        picture = "https://weighttraining.guide/exercises/incline-dumbbell-bench-press/";

        musclesUsed.add(new Chest());
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new Triceps());
    }
}
