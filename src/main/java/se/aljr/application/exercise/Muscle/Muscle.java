package se.aljr.application.exercise.Muscle;

import java.io.Serializable;

public abstract class Muscle implements Serializable {
	protected String name;

	@Override 
	public String toString() {
		return this.name;
	}
}
