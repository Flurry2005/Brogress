package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;



public class BenchPress extends Exercise {
	
	public BenchPress() {
		name = "Bench-press";
		info = "A compound exercise targeting the chest (pectoralis major),"
			+ " triceps, and shoulders (anterior deltoid). It's one of "
			+ "the primary upper-body pushing movements.";
		musclesUsed.add(new Chest());
		musclesUsed.add(new AnteriorDeltoid());
		musclesUsed.add(new Triceps());

	}
}
