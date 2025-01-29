package se.aljr.application.exercise.Program;

import java.util.ArrayList;

import se.aljr.application.exercise.Muscle.*;

public class MuscleList {
	private ArrayList <Muscle> muscles = new ArrayList<>();
	
	public MuscleList () {
		    muscles.add(new Abs());
		    muscles.add(new Adductors());
		    muscles.add(new AnteriorDeltoid());
		    muscles.add(new Biceps());
		    muscles.add(new Calves());
		    muscles.add(new Chest());
		    muscles.add(new Forearms());
		    muscles.add(new Glutes());
		    muscles.add(new Hamstrings());
		    muscles.add(new LateralDeltoid());
		    muscles.add(new Lats());
		    muscles.add(new LowerBack());
		    muscles.add(new Obliques());
		    muscles.add(new PosteriorDeltoid());
		    muscles.add(new Quadriceps());
		    muscles.add(new Traps());
		    muscles.add(new Triceps());
		}

	
	public ArrayList<Muscle> getMuscles() {
		return muscles;
	}
	}



