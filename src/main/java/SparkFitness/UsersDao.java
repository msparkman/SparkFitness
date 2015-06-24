package SparkFitness;

import org.springframework.stereotype.Repository;

/**
 * @author Matt Sparkman
 */
@Repository
public interface UsersDao {
    /**
     * Returns a user's ID.
     *
     * @return the user ID
     */
    int getUserId() throws Exception;
}