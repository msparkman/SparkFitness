package SparkFitness;

import SparkFitness.database.Routine;
import SparkFitness.server.WorkoutBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public List<Routine> getAllRoutines(@PathVariable("userId") int userId) {
        return workoutBo.getAllRoutines(userId);
    }

    @RequestMapping(value = "/getLastRoutine/{userId}", method = RequestMethod.GET)
    public Routine getLastRoutine(@PathVariable("userId") int userId) {
        return workoutBo.getLastRoutine(userId);
    }

    @RequestMapping(value = "/saveRoutine", method = RequestMethod.POST)
    public void saveRoutine(Routine routine) {
        workoutBo.saveRoutine(routine);
    }

    @RequestMapping(value = "/deleteRoutine/{routineId}", method = RequestMethod.PUT)
    public void deleteRoutine(@PathVariable("routineId") int routineId) {
        workoutBo.deleteRoutine(routineId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage() {
        return "Welcome to SparkFitness! View your last workout with /getLastRoutine/<userId>";
    }
}