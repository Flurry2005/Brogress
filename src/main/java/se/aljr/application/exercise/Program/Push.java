package se.aljr.application.exercise.Program;

import se.aljr.application.exercise.Excercise.BenchPress;
import se.aljr.application.exercise.Excercise.PushUp;

public class Push extends Program {

	public Push() {
		name = "Push";
		info = "Classic push program";
		excercises.add(new BenchPress());
		excercises.add(new PushUp());
	}

}
