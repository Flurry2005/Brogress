package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class Deadlift extends Exercise {

	public Deadlift() {
		name = "Deadlift";
		info = "A full-body compound lift primarily targeting "
				+ "the posterior chain, including hamstrings, "
				+ "glutes, lower back, trapezius, and forearms.";
		musclesUsed.add(new Glutes());
		musclesUsed.add(new Hamstrings());
		musclesUsed.add(new LowerBack());
		musclesUsed.add(new Traps());
		musclesUsed.add(new Forearms());
	}

}
