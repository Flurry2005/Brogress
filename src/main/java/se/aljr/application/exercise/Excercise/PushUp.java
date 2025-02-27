package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;

import javax.swing.*;


public class PushUp extends Exercise {
	
	public PushUp() {
		name = "Push-Up";

		info = "The push-up is a bodyweight exercise that primarily targets the chest, shoulders, and triceps while also engaging the core and stabilizing muscles. It helps build upper-body strength, endurance, and overall fitness.";

		form = "To perform a push-up, place your hands shoulder-width apart on the ground and extend your legs straight behind you. Lower your body until your chest nearly touches the floor, keeping your elbows at a slight angle. Push back up to the starting position while maintaining a straight body line and engaging your core.";

		imageIcon = new ImageIcon(ResourcePath.getResourcePath()+"ExerciseImages\\PushUp.png");
		musclesUsed.add(new Chest());
		musclesUsed.add(new AnteriorDeltoid());
		musclesUsed.add(new Triceps());
	
	}
}

