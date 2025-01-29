package se.aljr.application.exercise.Excercise;

import java.util.ArrayList;

import se.aljr.application.exercise.Muscle.Muscle;

public abstract class Exercise {
	protected String name;
	protected String info;
	protected ArrayList<Muscle> musclesUsed = new ArrayList<>();

	public String getName() {
		return this.name;
	}

	public String getInfo() {
		return this.info;
	}

	public String getMusclesUsed() {
		return musclesUsed.toString();
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
