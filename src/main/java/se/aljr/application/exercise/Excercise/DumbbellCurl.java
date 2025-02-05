package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Biceps;

public class DumbbellCurl extends Exercise {

	public DumbbellCurl() {
		name = "Dumbbell Curl";

		info = "The dumbbell curl is an isolation exercise that primarily targets the biceps brachii. \n" +
				"It is a staple for building arm strength and muscle definition by flexing the elbow joint.";

		form = "Good Form and Tips\n" +
				"Keep Elbows Stationary: Prevent movement to isolate the biceps.\n" +
				"Controlled Reps: Lift and lower the dumbbells slowly for maximum engagement.\n" +
				"Maintain a Neutral Spine: Avoid leaning back to keep tension on the biceps.";

		mistakes = "Common Mistakes to Avoid\n" +
				"Swinging the Dumbbells: Using momentum reduces biceps engagement.\n" +
				"Lifting the Elbows: Involves the shoulders and takes tension off the biceps.\n" +
				"Rushing Through Reps: Limits muscle activation and reduces effectiveness.";

		link = "https://musclewiki.com/dumbbells/male/biceps/dumbbell-curl";

		picture = "https://weighttraining.guide/exercises/two-arm-supinated-dumbbell-curl/";

		musclesUsed.add(new Biceps());
	}

}
