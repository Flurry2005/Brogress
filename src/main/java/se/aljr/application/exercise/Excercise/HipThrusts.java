package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.*;

public class HipThrusts extends Exercise {

    public HipThrusts() {
        name = "Hip Thrusts";

        musclesUsed.add(new Glutes());
        musclesUsed.add(new Hamstrings());
    }

}
