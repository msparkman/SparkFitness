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
     * Returns the last routine entered for a given user ID.
     *
     * @param userId the user ID
     * @return Routine containing the last routine entered
     */
    Routine getLastRoutineByUserId(int userId);

    /**
     * Inserts a routine into the database.
     *
     * @param routine the routine
     */
    void insertRoutine(Routine routine);

    /**
     * Deletes a routine from the routine table.
     *
     * @param routineId the routine ID
     */
    void deleteRoutine(int routineId);

    /**
     * Returns all the sets for the given routine ID.
     *
     * @param routineId the routine ID
     * @return List<Set> containing the routine's sets
     */
    List<Set> getSetsByRoutineId(int routineId);

    /**
     * Inserts a set into the database.
     *
     * @param set the set
     */
    void insertSet(Set set);

    /**
     * Deletes all sets that have the given routine ID.
     *
     * @param routineId the routine ID
     */
    void deleteSetsByRoutineId(int routineId);
}
