package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class SumoDeadlift extends Exercise {
    public SumoDeadlift() {
        name = "Sumo Deadlift";

        info = "The sumo deadlift is a variation of the traditional deadlift that emphasizes the glutes, hamstrings, and quadriceps. \n" +
                "It also engages the traps and lower back for stability and control, making it effective for developing lower-body power";

        form = "Good Form and Tips\n" +
                "Maintain a Neutral Spine: Keep your back straight throughout the lift.\n" +
                "Drive Through the Heels: Helps engage the glutes and quads for maximum power.\n" +
                "Keep the Bar Close to Your Body: Prevents strain on the lower back and maximizes control.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Rounding the Lower Back: Increases the risk of injury during the lift.\n" +
                "Bar Drifting Away from the Body: Reduces lifting efficiency and places strain on the lower back.\n" +
                "Incorrect Foot Placement: Too wide or narrow can reduce power and cause imbalance.";

        link = "https://musclewiki.com/barbell/male/lowerback/barbell-sumo-deadlift";

        picture = "https://weighttraining.guide/exercises/barbell-sumo-deadlift/";

        musclesUsed.add(new Glutes());
        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Quadriceps());
        musclesUsed.add(new Traps());
        musclesUsed.add(new LowerBack());
    }
}
