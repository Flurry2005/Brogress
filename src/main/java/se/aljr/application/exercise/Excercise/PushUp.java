package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;


public class PushUp extends Exercise {
	
	public PushUp() {
		name = "Push-Up";

		info = "The push-up is a bodyweight exercise that primarily targets the chest (pectoralis major), with secondary involvement from the anterior deltoids and triceps. \n" +
				"The core and stabilizer muscles are also engaged to maintain proper body alignment.";

		form = "Good Form and Tips\n" +
				"Maintain a Straight Line: Keep your body aligned from head to heels.\n" +
				"Engage the Core: Tighten your abs and glutes to support the spine.\n" +
				"Full Range of Motion: Lower until your chest is close to the ground, and fully extend at the top.";

		mistakes = "Common Mistakes to Avoid\n" +
				"Sagging Hips: Reduces core activation and increases strain on the lower back.\n" +
				"Flared Elbows: Increases shoulder strain and decreases pushing power.\n" +
				"Incomplete Range of Motion: Reduces muscle activation and limits effectiveness.";

		link = "https://musclewiki.com/bodyweight/male/chest/push-up";

		picture = "https://weighttraining.guide/exercises/push-up/";

		musclesUsed.add(new Chest());
		musclesUsed.add(new AnteriorDeltoid());
		musclesUsed.add(new Triceps());
	
	}
}

