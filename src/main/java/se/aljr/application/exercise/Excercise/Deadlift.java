package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class Deadlift extends Exercise {

	public Deadlift() {
		name = "Deadlift";
		info = "The deadlift is a full-body compound exercise primarily targeting the posterior chain, including the glutes, hamstrings, lower back, traps, and forearms.\n" +
				" It is a fundamental lift for building strength and muscle across the entire body.";

		form = "Good Form and Tips\n" +
				"Maintain a Neutral Spine: Keep your back straight throughout the lift.\n" +
				"Engage the Core: Tighten your abs to stabilize the lower back and prevent injuries.\n" +
				"Drive Through the Heels: Push with your legs to lift the weight while maintaining balance.";

		mistakes = "Common Mistakes to Avoid\n" +
				"Rounding the Back: Can lead to serious lower back injuries.\n" +
				"Using the Back Instead of the Legs: Over-reliance on the back increases strain and reduces efficiency.\n" +
				"Hyperextending at the Top: Overarching at lockout can harm the lower back.";

		link = "https://musclewiki.com/barbell/male/traps-middle/barbell-deadlift";

		picture = "https://weighttraining.guide/exercises/barbell-deadlift/";

		musclesUsed.add(new Glutes());
		musclesUsed.add(new Hamstrings());
		musclesUsed.add(new LowerBack());
		musclesUsed.add(new Traps());
		musclesUsed.add(new Forearms());
	}

}
