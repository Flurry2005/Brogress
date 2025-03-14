package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;

public class Squat extends Exercise {

	public Squat() {
		name = "Squat";

		info = "The squat is a compound lower-body exercise that primarily targets the quadriceps, hamstrings, and glutes while also engaging the core and lower back for stability. It helps build strength, muscle mass, and overall functional fitness.";

		form = "To perform a squat, stand with your feet shoulder-width apart and your toes slightly pointed out. Lower your hips by bending your knees and keeping your chest up until your thighs are parallel to the ground. Push through your heels to return to the starting position while maintaining proper posture and core engagement.";
		imageIcon = new ImageIcon(ResourcePath.getResourcePath("ExerciseImages/Squat.gif"));
		musclesUsed.add(new Glutes());
		musclesUsed.add(new Quadriceps());
		musclesUsed.add(new LowerBack());
		musclesUsed.add(new Hamstrings());
		musclesUsed.add(new Abs());
		
		
	}
}
