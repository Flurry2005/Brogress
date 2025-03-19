package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;

import javax.swing.*;


public class BenchPress extends Exercise {
	
	public BenchPress() {
		name = "Bench-press";

		info = "The bench press is a compound exercise that primarily targets the chest, shoulders, and triceps. It is a key movement for building upper body strength and muscle mass, commonly used in powerlifting, bodybuilding, and general fitness training.";

		form = "To perform a bench press, lie on a bench with feet planted firmly on the ground. Grip the bar slightly wider than shoulder-width, lower it to your chest while keeping your elbows at a slight angle, then press it back up until your arms are fully extended. Keep your core tight and maintain control throughout the movement.";

		musclesUsed.add(new Chest());
		musclesUsed.add(new AnteriorDeltoid());
		musclesUsed.add(new Triceps());

		imageIcon = new ImageIcon(ResourcePath.getResourcePath("ExerciseImages/BenchPress.gif"));
		imageIconPath = "ExerciseImages/BenchPress.gif";

	}
}
