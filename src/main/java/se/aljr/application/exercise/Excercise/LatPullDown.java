package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Biceps;
import se.aljr.application.exercise.Muscle.Lats;
import se.aljr.application.exercise.Muscle.PosteriorDeltoid;

public class LatPullDown extends Exercise {

	public LatPullDown() {
		name = "Lat Pulldown";

		info = "The lat pulldown is a compound pulling exercise primarily targeting the lats (latissimus dorsi). It also engages the biceps and posterior deltoids to aid in the pulling motion. \n" +
				"This exercise is commonly used to build upper back strength and width.";

		form = "Good Form and Tips\n" +
				"Maintain an Upright Posture: Keep your back straight and avoid excessive leaning.\n" +
				"Pull the Bar to the Chest: Aim for the top of the chest to maximize lat activation safely.\n" +
				"Control the Movement: Use slow, steady motions to ensure proper muscle engagement.";

		mistakes = "Common Mistakes to Avoid\n" +
				"Leaning Too Far Back: Shifts focus from the lats to the lower back.\n" +
				"Pulling the Bar Behind the Neck: Increases shoulder strain and injury risk.\n" +
				"Using Momentum: Reduces lat engagement and limits effectiveness.";

		link = "https://musclewiki.com/machine/male/lats/machine-pulldown";

		picture = "https://weighttraining.guide/exercises/wide-grip-lat-pull-down/";

		musclesUsed.add(new Lats());
		musclesUsed.add(new Biceps());
		musclesUsed.add(new PosteriorDeltoid());
	}

}
