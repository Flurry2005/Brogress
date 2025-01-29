package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class Squat extends Exercise {

	public Squat() {
		name = "Squat";
		info = "A compound lower-body exercise primarily "
			+ "targeting the quadriceps, glutes, and hamstrings,"
			+ "with stabilizer muscles like the core and lower back involved.";
		musclesUsed.add(new Glutes());
		musclesUsed.add(new Quadriceps());
		musclesUsed.add(new LowerBack());
		musclesUsed.add(new Hamstrings());
		musclesUsed.add(new Abs());
		
		
	}
}
