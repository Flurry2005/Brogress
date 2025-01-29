package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class SumoDeadlift extends Exercise {
    public SumoDeadlift() {
        name = "Sumo Deadlift";
        info = "A deadlift variation emphasizing glutes, hamstrings, and quadriceps with trap and lower back involvement.";
        musclesUsed.add(new Glutes());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Traps());
        musclesUsed.add(new LowerBack());
    }
}
