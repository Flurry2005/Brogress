package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.Biceps;
import se.aljr.application.exercise.Muscle.Forearms;

import javax.swing.*;

public class DumbbellCurl extends Exercise {

	public DumbbellCurl() {
		name = "Dumbbell Biceps Curl";

		info = "The dumbbell biceps curl is an isolation exercise that targets the biceps, helping to build arm strength and muscle definition. It is a fundamental movement in many strength-training routines.";

		form = "To perform a dumbbell biceps curl, hold a dumbbell in each hand with palms facing forward. Keep your elbows close to your torso and curl the weights up toward your shoulders, squeezing your biceps at the top. Lower the dumbbells back down in a controlled manner. Maintain a steady posture and avoid using momentum.";

		imageIcon = new ImageIcon(ResourcePath.getResourcePath()+"ExerciseImages\\DumbbellCurl.gif");

		musclesUsed.add(new Biceps());
		musclesUsed.add(new Forearms());

	}

}
