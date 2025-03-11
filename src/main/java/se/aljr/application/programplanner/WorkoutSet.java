package se.aljr.application.programplanner;

import se.aljr.application.exercise.Excercise.Exercise;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class WorkoutSet implements Serializable {

    @Serial
    private  static final long serialVersionUID = -7375990489509603276L;

    int number;
    int weight;
    int rep;
    int rir;
    Exercise exercise;

    public void setReps(Integer repAmount) {
        this.rep = repAmount;
    }

    public void setWeight(Integer weightAmount) {
        this.weight = weightAmount;
    }

    public void setRir(Integer rirAmount) {
        this.rir = rirAmount;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Exercise getExercise() {
        return this.exercise;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getReps() {
        return this.rep;
    }

    public int getRir() {
        return this.rir;
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public String toString() {
        return this.number + ". Reps: " + this.rep + " Weight: " + this.weight + " RIR: " + this.rir + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WorkoutSet that = (WorkoutSet) obj;
        return number == that.number && Objects.equals(exercise, that.exercise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, exercise);
    }
}
