package SparkFitness.server;

import SparkFitness.database.Routine;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An interface for gathering and returning workout information.
 *
 * @author Matt Sparkman
 */
@Service
public interface WorkoutBo {

    /**
     * Retrieves all the routines for a given user ID.
     *
     * @param userId the user ID
     * @return List<Routine> containing the user's routines
     */
    List<Routine> getAllRoutines(int userId);

    /**
     * Retrieves the last routine entered by the given user ID.
     *
     * @param userId the user ID
     * @return Routine containing the last entered routine
     */
    Routine getLastRoutine(int userId);

    /**
     * Saves a routine and its sets.
     *
     * @param routine the routine
     */
    void saveRoutine(Routine routine);

    /**
     * Deletes a routine and all of its sets.
     *
     * @param routineId the routine ID
     */
    void deleteRoutine(int routineId);

}
