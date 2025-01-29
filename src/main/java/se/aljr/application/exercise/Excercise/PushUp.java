package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;


public class PushUp extends Exercise {
	
	public PushUp() {
		name = "Push-Up";
		info = "A bodyweight exercise targeting the chest, "
			+ "triceps, and shoulders, with core and stabilizer muscles engaged.";
		musclesUsed.add(new Chest());
		musclesUsed.add(new AnteriorDeltoid());
		musclesUsed.add(new Triceps());
	
	}
}

