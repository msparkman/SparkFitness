package SparkFitness.server;

import SparkFitness.database.FitnessDAO;
import SparkFitness.database.Routine;
import SparkFitness.database.Set;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(WorkoutBoImpl.class);

    public void setFitnessDao(FitnessDAO fitnessDao) {
        this.fitnessDao = fitnessDao;
    }

    @Override
    public List<Routine> getAllRoutines(int userId) {
        final String METHOD_NAME = "getAllRoutines(int)";
        LOGGER.info("Begin " + METHOD_NAME);

        try {
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
        } finally {
            LOGGER.info("End " + METHOD_NAME);
        }
    }

    @Override
    public Routine getLastRoutine(int userId) {
        final String METHOD_NAME = "getLastRoutine(int)";
        LOGGER.info("Begin " + METHOD_NAME);

        try {
            if (userId < 1) {
                throw new IllegalArgumentException("Invalid user ID: " + userId);
            }

            Routine routine = fitnessDao.getLastRoutineByUserId(userId);
            // Get the sets for this routine
            routine.setSetList(fitnessDao.getSetsByRoutineId(routine.getRoutineId()));

            return routine;
        } finally {
            LOGGER.info("End " + METHOD_NAME);
        }
    }

    @Override
    public void saveRoutine(Routine routine) {
        final String METHOD_NAME = "saveRoutine(Routine)";
        LOGGER.info("Begin " + METHOD_NAME);

        try {
            if (routine == null) {
                throw new IllegalArgumentException("No routine given.");
            } else if (routine.getUserId() < 1) {
                throw new IllegalArgumentException("Invalid user ID given: " + routine.getUserId());
            }

            // Insert the routine
            int routineId = fitnessDao.insertRoutine(routine);

            // Set the routine ID and insert each set
            for (Set set : routine.getSetList()) {
                set.setRoutineId(routineId);
                fitnessDao.insertSet(set);
            }
        } finally {
            LOGGER.info("End " + METHOD_NAME);
        }
    }

    @Override
    public void deleteRoutine(int routineId) {
        final String METHOD_NAME = "deleteRoutine(int)";
        LOGGER.info("Begin " + METHOD_NAME);

        try {
            if (routineId < 1) {
                throw new IllegalArgumentException("Invalid routine ID given: " + routineId);
            }

            // Delete the routine
            fitnessDao.deleteRoutine(routineId);

            // Delete the sets that have the same routine ID
            fitnessDao.deleteSetsByRoutineId(routineId);
        } finally {
            LOGGER.info("End " + METHOD_NAME);
        }
    }
}