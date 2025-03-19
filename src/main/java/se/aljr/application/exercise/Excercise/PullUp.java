package se.aljr.application.exercise.Excercise;

import se.aljr.application.ResourcePath;
import se.aljr.application.exercise.Muscle.*;

import javax.swing.*;

public class PullUp extends Exercise {
    public PullUp() {
        name = "Pull-Up";

        info = "The pull-up is a compound upper-body exercise that primarily targets the latissimus dorsi (lats), while also engaging the biceps, shoulders, and upper back. It helps build upper-body strength, muscle endurance, and grip strength.";

        form = "To perform a pull-up, grip a pull-up bar with your hands slightly wider than shoulder-width, palms facing away. Pull yourself up until your chin clears the bar, then lower yourself back down with control. Keep your core engaged and avoid using momentum throughout the movement.";

        imageIcon = new ImageIcon(ResourcePath.getResourcePath("ExerciseImages/PullUp.gif"));
        imageIconPath = "ExerciseImages/PullUp.gif";

        musclesUsed.add(new Lats());
        musclesUsed.add(new Biceps());
        musclesUsed.add(new PosteriorDeltoid());
        musclesUsed.add(new Forearms());
        musclesUsed.add(new Traps());
    }
}
