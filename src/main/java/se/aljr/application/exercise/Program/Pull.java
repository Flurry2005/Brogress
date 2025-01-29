package se.aljr.application.exercise.Program;

import se.aljr.application.exercise.Excercise.Deadlift;
import se.aljr.application.exercise.Excercise.DumbbellCurl;
import se.aljr.application.exercise.Excercise.DumbbellRow;
import se.aljr.application.exercise.Excercise.LatPullDown;

public class Pull extends Program {

	public Pull() {
		name = "Pull";
		info = "Classic pull program";
		excercises.add(new Deadlift());
		excercises.add(new LatPullDown());
		excercises.add(new DumbbellRow());
		excercises.add(new DumbbellCurl());
	}
}
