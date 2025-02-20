package se.aljr.application.exercise.Program;

import java.util.ArrayList;

import se.aljr.application.exercise.Excercise.*;

public class Exercises {
	private ArrayList<Exercise> exercises = new ArrayList<>();

	public Exercises() {
		exercises.add(new BarbellRow());
		exercises.add(new BenchPress());
		exercises.add(new BentOverRow());
		exercises.add(new CableRow());
		exercises.add(new CableSideLateralRaises());
		exercises.add(new ChestFly());
		exercises.add(new Deadlift());
		exercises.add(new DumbbellCurl());
		exercises.add(new DumbbellPress());
		exercises.add(new DumbbellRow());
		exercises.add(new DumbbellSideLateralRaises());
		exercises.add(new InclineBenchPress());
		exercises.add(new InclineDumbbellPress());
		exercises.add(new LatPullDown());
		exercises.add(new LegPress());
		exercises.add(new Lunge());
		exercises.add(new OverheadPress());
		exercises.add(new Plank());
		exercises.add(new PullUp());
		exercises.add(new PushUp());
		exercises.add(new RomanianDeadlift());
		exercises.add(new SeatedCalfRaise());
		exercises.add(new SidePlank());
		exercises.add(new Squat());
		exercises.add(new StepUp());
		exercises.add(new SumoDeadlift());
		exercises.add(new TricepsCableOverheadExtensions());
		exercises.add(new TricepsCablePushdowns());
		exercises.add(new TricepsFrenchPress());
		exercises.add(new HipThrusts());

	}

	public ArrayList<Exercise> getList() {
		return exercises;
	}

	public void removeGif(){
		for(Exercise exercise: exercises){
			exercise.removeImageIcon();
		}
	}

}
