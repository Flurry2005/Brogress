package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.Biceps;
import se.aljr.application.exercise.Muscle.Lats;
import se.aljr.application.exercise.Muscle.PosteriorDeltoid;

import javax.swing.*;

public class LatPullDown extends Exercise {

	public LatPullDown() {
		name = "Cable Lat Pulldown";

		info = "The cable lat pulldown is a compound exercise that primarily targets the latissimus dorsi (lats), while also engaging the biceps and upper back muscles. It helps build back strength, improve posture, and enhance pulling power.";

		form = "To perform a cable lat pulldown, sit at a lat pulldown machine and grip the bar slightly wider than shoulder-width. Pull the bar down toward your chest while keeping your back straight and elbows pointed downward. Squeeze your lats at the bottom, then slowly return the bar to the starting position with control.";

		imageIcon = new ImageIcon(ResourcePath.getResourcePath()+"ExerciseImages\\LatPullDown.gif");

		musclesUsed.add(new Lats());
		musclesUsed.add(new Biceps());
		musclesUsed.add(new PosteriorDeltoid());
	}

}
