package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;

public class SumoDeadlift extends Exercise {
    public SumoDeadlift() {
        name = "Sumo Deadlift";

        info = "The sumo deadlift is a compound exercise that primarily targets the glutes, hamstrings, quadriceps, and lower back while also engaging the core and grip strength. Its wider stance reduces strain on the lower back and emphasizes the legs more than a conventional deadlift.";

        form = "To perform a sumo deadlift, stand with your feet wider than shoulder-width apart and toes pointed slightly outward. Grip the barbell with hands inside your knees, keep your back straight, and drive through your heels to lift the bar while extending your hips. Lock out at the top, then lower the bar back down with control.";

        musclesUsed.add(new Glutes());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Traps());
        musclesUsed.add(new LowerBack());
    }
}
