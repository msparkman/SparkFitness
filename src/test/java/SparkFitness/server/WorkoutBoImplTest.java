package SparkFitness.server;

import SparkFitness.database.FitnessDAO;
import SparkFitness.database.Routine;
import SparkFitness.database.Set;
import com.beust.jcommander.internal.Lists;
import org.mockito.Mockito;
import static org.mockito.Mockito.stub;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Unit tests for {@link SparkFitness.server.WorkoutBoImpl}
 */
public class WorkoutBoImplTest {
    WorkoutBoImpl workoutBoImpl;
    FitnessDAO fitnessDao;

    @BeforeTest
    public void setUp() {
        workoutBoImpl = new WorkoutBoImpl();
        fitnessDao = Mockito.mock(FitnessDAO.class);
        workoutBoImpl.setFitnessDao(fitnessDao);
    }

    @Test(enabled = false)
    public void testGetAllRoutines() {
        workoutBoImpl.getAllRoutines(1);
    }

    @Test(enabled = false)
    public void testGetLastRoutine() {
        // TODO need to figure out how to stub db method return values to test against
        stub(new Object());
        workoutBoImpl.getLastRoutine(1);
    }

    @Test(enabled = false)
    public void testSaveRoutine() {
        // TODO need to create test constants for each of these values
        Routine routine = new Routine();
        routine.setRoutineId(1);
        routine.setUserId(1);
        routine.setName("test");
        routine.setType("chest");
        routine.setDate(new Date());

        Set set = new Set();
        set.setSetId(1);
        set.setRoutineId(1);
        set.setNumberOfReps(10);
        set.setComment("this is a test");
        set.setWeight(100);

        routine.setSetList(Lists.newArrayList(set));
        workoutBoImpl.saveRoutine(routine);
    }

    @Test(enabled = false)
    public void testDeleteRoutine() {
        workoutBoImpl.deleteRoutine(1);
    }

    @AfterClass(enabled = false)
    public void tearDown() {
    }
}