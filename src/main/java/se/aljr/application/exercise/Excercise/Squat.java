package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class Squat extends Exercise {

	public Squat() {
		name = "Squat";

		info = "The squat is a compound lower-body exercise that primarily targets the quadriceps, glutes, and hamstrings. \n" +
				"It also involves stabilizer muscles like the core and lower back, making it essential for building strength and functional movement.\n\n";

		form = "Good Form and Tips\n" +
				"Keep Your Chest Up and Back Straight: Helps maintain proper alignment and balance.\n" +
				"Drive Through the Heels: Engages the glutes and quads while keeping stability.\n" +
				"Engage the Core: Stabilizes the spine and prevents lower back strain.";

		mistakes = "Common Mistakes to Avoid\n" +
				"Knees Caving Inward: Can lead to knee strain or injury.\n" +
				"Rounding the Lower Back: Puts stress on the lower spine and increases injury risk.\n" +
				"Heels Lifting Off the Ground: Shifts the load to the knees, reducing stability and power.";

		link = "https://musclewiki.com/barbell/male/glutes/barbell-squat";

		picture = "https://weighttraining.guide/exercises/barbell-sumo-squat/";

		musclesUsed.add(new Glutes());
		musclesUsed.add(new Quadriceps());
		musclesUsed.add(new LowerBack());
		musclesUsed.add(new Hamstrings());
		musclesUsed.add(new Abs());
		
		
	}
}
