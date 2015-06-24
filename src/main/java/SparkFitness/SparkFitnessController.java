package SparkFitness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller to handle any REST calls to the application.
 *
 * @author Matt Sparkman
 */
@RestController
public class SparkFitnessController {
    @Autowired
    UsersDao usersDao;

    @RequestMapping("/")
    public String index() {
        String message = "Welcome to SparkFitness!";
        try {
            message = "User ID: " + usersDao.getUserId();
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        return message;
    }
}