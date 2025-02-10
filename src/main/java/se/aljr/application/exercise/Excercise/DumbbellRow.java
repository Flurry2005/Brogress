package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class DumbbellRow extends Exercise {
    public DumbbellRow() {
        name = "Dumbbell Row";

        info = "The dumbbell row is a single-arm pulling exercise that primarily targets the lats (latissimus dorsi), biceps, and traps (trapezius). \n" +
                "It also involves the posterior deltoids and lower back for stabilization and control.";

        form = "Good Form and Tips" +
                "Keep Your Back Straight: Maintain a neutral spine throughout the movement." +
                "Engage the Core: Stabilize your body to protect the lower back." +
                "Control the Dumbbell: Avoid fast, jerky motions—lift and lower in a controlled manner.";

        mistakes = "Common Mistakes to Avoid" +
                "Rounding the Back: Can strain the lower back and cause injury." +
                "Overusing Momentum: Swinging the dumbbell reduces muscle engagement." +
                "Letting the Shoulder Drop: Reduces back activation and leads to poor form.";

        link = "https://musclewiki.com/dumbbells/male/biceps/dumbbell-kneeling-single-arm-row";

        picture = "https://weighttraining.guide/exercises/bent-over-dumbbell-row/";

        musclesUsed.add(new Lats());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new Traps());
        musclesUsed.add(new LowerBack());
    }
}
