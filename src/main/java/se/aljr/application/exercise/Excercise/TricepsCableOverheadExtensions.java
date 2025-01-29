package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Triceps;

public class TricepsCableOverheadExtensions extends Exercise {
    public TricepsCableOverheadExtensions() {
        name = "Overhead-Extensions (Cable)";
        info = "A triceps isolation movement performed overhead using a cable machine.";
        musclesUsed.add(new Triceps());
    }
}
