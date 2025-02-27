package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Triceps;

public class TricepsCableOverheadExtensions extends Exercise {
    public TricepsCableOverheadExtensions() {
        name = "Cable Overhead Extensions";

        info = "The cable overhead extension is an isolation exercise that primarily targets the triceps, helping to build arm strength and definition. It provides constant tension throughout the movement, making it an effective alternative to free-weight extensions.";

        form = "To perform a cable overhead extension, attach a rope handle to a low pulley and grip it with both hands. Face away from the machine and extend your arms overhead. Keeping your elbows close to your head, lower the rope behind your head, then extend your arms back up to the starting position. Maintain control and engage your core throughout the movement.";

        musclesUsed.add(new Triceps());
    }
}
