package SparkFitness;

import SparkFitness.database.FitnessDAO;
import SparkFitness.database.Routine;
import SparkFitness.database.Set;
import SparkFitness.server.WorkoutBo;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;

/**
 * A controller to handle any REST calls to the application.
 *
 * @author Matt Sparkman
 */
@RestController
public class SparkFitnessController {

    @Autowired
    private WorkoutBo workoutBo;

    @RequestMapping(value = "/getAllRoutines/{userId}", method = RequestMethod.GET)
    public String getAllRoutines(@PathVariable("userId") int userId) {
        StringBuilder message = new StringBuilder();
        try {
            List<Routine> routineList = workoutBo.getAllRoutines(userId);

            // Loop through and print out the routines
            for (Routine routine : routineList) {
                message.append("User ID: ").append(routine.getUserId()).append("\n");
                message.append("Workout: ").append(routine.getType()).append(" ").append(routine.getName()).append("\n");
                message.append("Date: ").append(routine.getDate() != null ? routine.getDate() : "").append("\n");

                // Loop through and print out the sets
                int setNumber = 1;

                List<Set> setList = routine.getSetList();

                if (setList != null) {
                    for (Set set : routine.getSetList()) {
                        message.append("Set #").append(setNumber).append("\n");
                        message.append("Number of Reps: ").append(set.getNumberOfReps()).append("\n");
                        message.append("Weight: ").append(set.getWeight()).append("\n");
                        message.append("Comment: ").append(set.getComment()).append("\n");

                        setNumber++;
                    }
                }

                // Double space for cleanliness
                message.append("\n\n");
            }
        } catch (Exception ex) {
            message = new StringBuilder(ex + "\n");
        }

        return message.toString();
    }

    /*@RequestMapping(value = "/getLastRoutine/{userId}", method = RequestMethod.GET)
    public String getLastRoutine(@PathVariable("userId") int userId) {
        String message;
        try {
            Routine lastRoutine = fitnessDao.getLastRoutine(userId);
            message = "Last routine type: " + lastRoutine.getType();
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        return message;
    }*/

    /*@RequestMapping(value = "/saveWorkout", method = RequestMethod.POST)
    public void saveWorkout(int userId) {

    }*/
}