package se.aljr.application.exercise.Excercise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import se.aljr.application.UserData;
import se.aljr.application.exercise.Muscle.Muscle;

public abstract class Exercise implements Serializable {
    protected String name;
    protected String info;
    protected String form;
    protected String mistakes;
    protected String link;
    protected String picture;
    protected ArrayList<Muscle> musclesUsed = new ArrayList<>();

    public String getName() {
            return this.name;
    }

    public String getInfo() {
        return this.info;
    }

    public String getForm() {
        return this.form;
    }

    public String getMistakes() {
        return this.mistakes;
    }

    public String getMusclesUsed() {
        return musclesUsed.toString();
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Exercise exercise = (Exercise) obj;
        return name.equals(exercise.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
