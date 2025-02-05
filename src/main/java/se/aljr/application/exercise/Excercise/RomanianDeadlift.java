package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class RomanianDeadlift extends Exercise {
    public RomanianDeadlift() {
        name = "Romanian Deadlift";

        info = "The Romanian deadlift (RDL) is a posterior chain exercise that primarily targets the hamstrings, glutes, and lower back, with secondary involvement of the forearms and calves. \n" +
                "This exercise focuses on controlled movement, promoting flexibility and strength in the posterior chain.";

        form = "Good Form and Tips\n" +
                "Maintain a Neutral Spine: Keep your back straight throughout the movement.\n" +
                "Hinge at the Hips: Focus on pushing the hips back while keeping a slight bend in the knees.\n" +
                "Control the Descent: Lower the bar slowly to maximize muscle tension and avoid injury.";

        mistakes = "Common Mistakes to Avoid\n" +
                "Rounding the Back: Increases the risk of lower back injury.\n" +
                "Overextending at the Top: Hyperextending the hips can stress the lower back.\n" +
                "Bending the Knees Too Much: Turns it into a regular deadlift, reducing hamstring engagement.";

        link = "https://musclewiki.com/dumbbells/male/lowerback/dumbbell-romanian-deadlift";

        picture = "https://weighttraining.guide/exercises/dumbbell-straight-leg-deadlift/";

        musclesUsed.add(new Hamstrings());
        musclesUsed.add(new Glutes());
        musclesUsed.add(new LowerBack());
        musclesUsed.add(new Forearms());
        musclesUsed.add(new Calves());
    }
}
