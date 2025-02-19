package se.aljr.application.exercise.Excercise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import se.aljr.application.ResourcePath;
import se.aljr.application.UserData;
import se.aljr.application.exercise.Muscle.Muscle;

import javax.swing.*;

public class Exercise implements Serializable {
    protected String name;
    protected String info;
    protected String form;
    protected String mistakes;
    protected String link;
    protected String picture;
    protected ArrayList<Muscle> musclesUsed = new ArrayList<>();
    protected ImageIcon imageIcon;

    public String getName() {
            return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }

    public void createExercise(String name, String info, ArrayList<Muscle> musclesUsed) {
        this.name = name;
        this.info = info;
        this.musclesUsed = musclesUsed;
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
