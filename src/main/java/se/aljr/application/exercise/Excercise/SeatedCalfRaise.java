package se.aljr.application.exercise.Excercise;

import se.aljr.application.exercise.Muscle.Calves;
import se.aljr.application.exercise.Muscle.Hamstrings;

public class SeatedCalfRaise extends Exercise {
    public SeatedCalfRaise() {
        name = "Seated Calf Raise";

        info = "The seated calf raise is an isolation exercise that primarily targets the soleus muscle of the calves. It helps build lower leg strength, muscle definition, and endurance, benefiting athletic performance and overall leg development.";

        form = "To perform a seated calf raise, sit on a calf raise machine with the balls of your feet on the platform and your knees positioned under the pad. Push through your toes to lift your heels as high as possible, squeezing your calves at the top. Lower your heels back down in a controlled manner and repeat.";

        musclesUsed.add(new Calves());
        musclesUsed.add(new Hamstrings());
    }
}
