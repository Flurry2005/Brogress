package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Triceps;

public class TricepsFrenchPress extends Exercise {
    public TricepsFrenchPress() {
        name = "French-Press";
        info = "A triceps-focused exercise performed with a dumbbell or barbell overhead.";
        musclesUsed.add(new Triceps());
    }
}
