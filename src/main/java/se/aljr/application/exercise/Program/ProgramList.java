package se.aljr.application.exercise.Program;

import java.util.ArrayList;


public class ProgramList {
	private ArrayList <Program> program = new ArrayList<>();
	
	public ProgramList () {
		program.add(new Push());
		program.add(new Pull());
		
	}
	
	public ArrayList<Program> getProgramList() {
		return program;
	}
}
