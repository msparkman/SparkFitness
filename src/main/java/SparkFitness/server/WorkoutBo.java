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
    List getAllRoutines(int userId);

}
