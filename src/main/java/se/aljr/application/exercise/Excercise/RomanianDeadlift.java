package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class RomanianDeadlift extends Exercise {
    public RomanianDeadlift() {
        name = "Romanian Deadlift";
        info = "A posterior chain exercise emphasizing hamstrings, glutes, and lower back with controlled movement.";
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new LowerBack());
        musclesUsed.add(new Forearms());
        musclesUsed.add(new Calves());
    }
}
