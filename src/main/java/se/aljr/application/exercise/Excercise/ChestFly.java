package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Biceps;
import se.aljr.application.exercise.Muscle.Chest;

public class ChestFly extends Exercise {
    public ChestFly() {
        name = "Chest Fly (Cable)";

        info = "The cable chest fly is an isolation exercise targeting the chest (pectoralis major), with support from the anterior deltoids and biceps. \n" +
                "This movement emphasizes chest development, focusing on muscle stretch and contraction.";

        form = "Good Form and Tips" +
                "Maintain a Slight Bend in the Elbows: Protects the joints while maximizing chest activation." +
                "Use Controlled Movements: Slowly bring the handles together and maintain tension throughout." +
                "Avoid Excessive Back Arching: Keep your core engaged to protect the lower back.";

        mistakes = "Common Mistakes to Avoid" +
                "Overstretching the Arms: Can strain the shoulders or cause injury." +
                "Using Too Much Weight: Can lead to poor form and limit the full range of motion." +
                "Locking the Elbows: Reduces chest engagement and risks elbow strain.";

        link = "https://musclewiki.com/cables/male/chest/cable-pec-fly";

        picture = "https://weighttraining.guide/exercises/cable-cross-over/";

        musclesUsed.add(new Chest());
        musclesUsed.add(new AnteriorDeltoid());
        musclesUsed.add(new Biceps());
    }
}
