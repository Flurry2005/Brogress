package se.aljr.application.exercise.Program;

import java.util.ArrayList;

import se.aljr.application.exercise.Excercise.Exercise;

public abstract class Program {
	
	String name;
	String info;
	protected ArrayList<Exercise> excercises = new ArrayList<>();
	
	public String getName() {
		return this.name;
	}

	public String getInfo() {
		return this.info;
	}

	public String getExcercises() {
		return excercises.toString();
	}
}
