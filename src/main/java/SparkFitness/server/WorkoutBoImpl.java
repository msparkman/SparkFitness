package SparkFitness.server;

import SparkFitness.database.FitnessDAO;
import SparkFitness.database.Routine;
import SparkFitness.database.Set;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A class for gathering and returning workout information.
 *
 * @author Matt Sparkman
 */
@Service
public class WorkoutBoImpl implements WorkoutBo {
    @Autowired
    FitnessDAO fitnessDao;

    @Override
    public List<Routine> getAllRoutines(int userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("Invalid user ID: " + userId);
        }

        List<Routine> routinesList = fitnessDao.getRoutinesByUserId(userId);

        if (routinesList == null) {
            return Lists.newArrayList();
        }

        // Loop through each routine and get the sets
        for (Routine routine : routinesList) {
            routine.setSetList(fitnessDao.getSetsByRoutineId(routine.getRoutineId()));
        }

        return routinesList;
    }

    @Override
    public Routine getLastRoutine(int userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("Invalid user ID: " + userId);
        }

        Routine routine = fitnessDao.getLastRoutineByUserId(userId);
        // Get the sets for this routine
        routine.setSetList(fitnessDao.getSetsByRoutineId(routine.getRoutineId()));

        return routine;
    }

    @Override
    public void saveRoutine(Routine routine) {
        if (routine == null) {
            throw new IllegalArgumentException("No routine given.");
        } else if (routine.getUserId() < 1) {
            throw new IllegalArgumentException("Invalid user ID given: " + routine.getUserId());
        }

        // Insert the routine
        fitnessDao.insertRoutine(routine);

        // Insert each set
        routine.getSetList().forEach(fitnessDao::insertSet);
    }

    @Override
    public void deleteRoutine(int routineId) {
        if (routineId < 1) {
            throw new IllegalArgumentException("Invalid routine ID given: " + routineId);
        }

        // Delete the routine
        fitnessDao.deleteRoutine(routineId);

        // Delete the sets that have the same routine ID
        fitnessDao.deleteSetsByRoutineId(routineId);
    }
}
