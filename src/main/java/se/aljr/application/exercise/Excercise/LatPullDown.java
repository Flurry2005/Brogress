package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Biceps;
import se.aljr.application.exercise.Muscle.Lats;
import se.aljr.application.exercise.Muscle.PosteriorDeltoid;

public class LatPullDown extends Exercise {

	public LatPullDown() {
		name = "Lat Pulldown";
		info = "A compound pulling exercise targeting the lats"
			+ " (latissimus dorsi), biceps, and rear deltoids.";
		musclesUsed.add(new Lats());
		musclesUsed.add(new Biceps());
		musclesUsed.add(new PosteriorDeltoid());
	}

}
