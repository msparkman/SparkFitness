package SparkFitness;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Matt Sparkman
 */
@Repository
public interface UsersDAO {
    /**
     * Returns all the user IDs.
     *
     * @return List<Integer> containing the user IDs
     */
    List<Integer> getUserIds() throws Exception;
}