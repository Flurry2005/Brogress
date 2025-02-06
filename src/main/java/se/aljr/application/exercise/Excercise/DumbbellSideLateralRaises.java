package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.LateralDeltoid;
import se.aljr.application.exercise.Muscle.Traps;

public class DumbbellSideLateralRaises extends Exercise {
    public DumbbellSideLateralRaises() {
        name = "Lateral Raises";

        info = "Dumbbell side lateral raises are an isolation exercise that targets the lateral deltoids, with secondary activation of the traps and anterior deltoids. \n" +
                "This exercise is great for building shoulder width and definition.";

        form = "Good Form and Tips\n" +
                "Maintain a Slight Bend in the Elbows: Helps protect the joints and focus on the deltoids.\n" +
                "Controlled Movement: Lift slowly and avoid using momentum.\n" +
                "Keep Shoulders Relaxed: Prevent trap overactivation to ensure proper lateral deltoid engagement.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Shrugging the Shoulders: Over-engages the traps and reduces lateral deltoid activation.\n" +
                "Swinging the Dumbbells: Using momentum decreases effectiveness.\n" +
                "Lifting Too High: Can strain the shoulder joints.";

        link = "https://musclewiki.com/dumbbells/male/shoulders/dumbbell-lateral-raise";

        picture = "https://weighttraining.guide/exercises/kettlebell-lateral-raise/";

        musclesUsed.add(new LateralDeltoid());
        musclesUsed.add(new Traps());
        musclesUsed.add(new AnteriorDeltoid());
    }
}
