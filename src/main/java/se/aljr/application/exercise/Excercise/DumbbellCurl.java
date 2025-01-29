package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Biceps;

public class DumbbellCurl extends Exercise {

	public DumbbellCurl() {
		name = "Dumbbell Curl";
		info = "An isolation exercise targeting the biceps brachii.";
		musclesUsed.add(new Biceps());
	}

}
