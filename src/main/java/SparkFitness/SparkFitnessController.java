package SparkFitness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
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
    UsersDAO usersDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        String message;
        try {
            message = "User ID: " + usersDao.getUserIds();
        } catch (Exception ex) {
            message = ex.getMessage();
        }
        return message;
    }

    @RequestMapping(value = "/getLastRoutine", method = RequestMethod.GET)
    public void getLastRoutine() {

    }
}