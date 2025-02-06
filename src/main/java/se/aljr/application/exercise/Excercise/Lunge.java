package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class Lunge extends Exercise {
    public Lunge() {
        name = "Lunge";

        info = "The lunge is a unilateral lower-body exercise that primarily targets the glutes, quadriceps, and hamstrings, with additional involvement from the calves and core muscles for stability. \n" +
                "This exercise is excellent for improving balance, strength, and coordination.";

        form = "Good Form and Tips\n" +
                "Maintain a Straight Back: Keep your torso upright and avoid leaning forward.\n" +
                "Ensure Proper Step Distance: Step far enough forward to allow the front thigh to be parallel to the ground.\n" +
                "Engage the Core: Helps maintain balance and stability throughout the movement.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Knee Extending Past Toes: Can put excess strain on the knee joint.\n" +
                "Leaning Forward: Shifts the load away from the lower body and onto the lower back.\n" +
                "Inconsistent Step Length: Taking steps too long or too short can reduce effectiveness.";

        link = "https://musclewiki.com/bodyweight/male/glutes/forward-lunges";

        picture = "https://weighttraining.guide/exercises/rear-lunge/";

        musclesUsed.add(new Glutes());
        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Calves());
        musclesUsed.add(new Abs());
    }
}
