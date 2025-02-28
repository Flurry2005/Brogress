package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;

public class Deadlift extends Exercise {

	public Deadlift() {
		name = "Deadlift";

		info = "The deadlift is a compound exercise that targets multiple muscle groups, including the posterior chain (lower back, glutes, hamstrings), core, and grip strength. It is one of the most effective movements for building overall strength, power, and athletic performance.";

		form = "To perform a deadlift, stand with feet hip-width apart, grip the barbell just outside your knees, and keep your back straight. Drive through your heels, lifting the bar while keeping it close to your body, and stand up fully before lowering it back down with control. Engage your core and maintain proper posture throughout the movement.";

		musclesUsed.add(new Glutes());
		musclesUsed.add(new Hamstrings());
		musclesUsed.add(new LowerBack());
		musclesUsed.add(new Traps());
		musclesUsed.add(new Forearms());

		imageIcon = new ImageIcon(ResourcePath.getResourcePath() +"ExerciseImages\\Deadlift.png");
	}

}
