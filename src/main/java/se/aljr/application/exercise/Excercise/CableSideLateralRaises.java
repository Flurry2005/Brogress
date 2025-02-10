package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.LateralDeltoid;
import se.aljr.application.exercise.Muscle.Traps;

public class CableSideLateralRaises extends Exercise {
    public CableSideLateralRaises() {
        name = "Lateral Raises (Cable)";

        info = "Cable side lateral raises are an isolation exercise that targets the lateral deltoids, while also engaging the traps and anterior deltoids. \n" +
                "This exercise helps build shoulder width and overall upper body aesthetics.";

        form = "Good Form and Tips\n" +
                "Maintain a Slight Bend in the Elbows: Protects the joints and maximizes muscle tension.\n" +
                "Controlled Movements: Lift slowly and avoid using momentum.\n" +
                "Keep Shoulders Relaxed: Focus on using the lateral deltoids, not the traps, to lift the weight.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Shrugging the Shoulders: Over-engages the traps, reducing lateral deltoid activation.\n" +
                "Swinging the Weights: Using momentum decreases muscle engagement.\n" +
                "Lifting Too High: Lifting above shoulder level can strain the shoulder joint.";

        link = "https://musclewiki.com/cables/male/shoulders/cable-low-single-arm-lateral-raise";

        picture = "https://weighttraining.guide/exercises/cable-one-arm-lateral-raise/";

        musclesUsed.add(new LateralDeltoid());
        musclesUsed.add(new Traps());
        musclesUsed.add(new AnteriorDeltoid());
    }
}
