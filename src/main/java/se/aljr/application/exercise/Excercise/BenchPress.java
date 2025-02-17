package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.AnteriorDeltoid;
import se.aljr.application.exercise.Muscle.Chest;
import se.aljr.application.exercise.Muscle.Triceps;

import javax.swing.*;
import java.io.Serializable;


public class BenchPress extends Exercise {
	
	public BenchPress() {
		name = "Bench-press";

		info = "The bench press is a compound upper-body exercise that primarily targets the chest (pectoralis major) while also engaging the triceps and \n" +
				"anterior deltoids (front shoulders). It is a fundamental pushing exercise used to develop upper-body strength and muscle mass.";

		form = "Good Form and Tips\n" +
				"Keep a Moderate Arch in the Lower Back: Maintain natural spine alignment without overextending.\n" +
				"Tuck Elbows at 45 Degrees: Protects the shoulders while maximizing chest activation.\n" +
				"Controlled Bar Movement: Lower the bar slowly and press it back up explosively while keeping control.";

		mistakes = "Common Mistakes to Avoid\n" +
				"Lifting with Flared Elbows: Can strain the shoulders and increase injury risk.\n" +
				"Arching the Lower Back Excessively: Leads to poor form and potential lower back injury.\n" +
				"Bouncing the Bar Off the Chest: Reduces control and risks chest injuries.";

		link = "https://musclewiki.com/barbell/male/chest/barbell-bench-press";

		picture = "https://weighttraining.guide/exercises/wide-reverse-grip-barbell-bench-press/";

		musclesUsed.add(new Chest());
		musclesUsed.add(new AnteriorDeltoid());
		musclesUsed.add(new Triceps());

		imageIcon = new ImageIcon(ResourcePath.getResourcePath() +"ExerciseImages\\BenchPress.gif");

	}
}
