package SparkFitness.database;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * An all-encompassing interface for accessing any workout information from the database.
 *
 * @author Matt Sparkman
 */
@Repository
public interface FitnessDAO {

    /**
     * Returns all the routines for a given user ID.
     *
     * @param userId the user ID
     * @return List<Routine> containing the user's routines
     */
    List<Routine> getRoutinesByUserId(int userId);

    /**
     * Returns all the sets for the given routine ID.
     *
     * @param routineId the routine ID
     * @return List<Set> containing the routine's sets
     */
    List<Set> getSetsByRoutineId(int routineId);
}
