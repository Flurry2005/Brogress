package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Abs;
import se.aljr.application.exercise.Muscle.LowerBack;
import se.aljr.application.exercise.Muscle.Obliques;

public class Plank extends Exercise {
    public Plank() {
        name = "Plank";

        info = "The plank is an isometric core exercise that focuses on the abs, obliques, and lower back for stabilization. \n" +
                "It helps develop core strength and stability, supporting overall functional movement and posture.";

        form = "Good Form and Tips\n" +
                "Keep a Neutral Spine: Ensure your back is straight and aligned with your head and hips.\n" +
                "Engage the Core: Tighten the abs and glutes to stabilize your body.\n" +
                "Breathe Steadily: Maintain steady breathing to sustain endurance and proper form.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Sagging or Arching the Lower Back: Increases strain on the lower back and reduces core activation.\n" +
                "Holding Breath: Limits endurance and can lead to improper form.\n" +
                "Tilting the Hips Up: Reduces the engagement of the core muscles.";

        link = "https://musclewiki.com/bodyweight/male/abdominals/hand-plank";

        picture = "https://weighttraining.guide/exercises/weighted-front-plank/";

        musclesUsed.add(new Abs());
        musclesUsed.add(new Obliques());
        musclesUsed.add(new LowerBack());
    }
}
