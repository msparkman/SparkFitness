package SparkFitness.server;

import SparkFitness.database.FitnessDAO;
import SparkFitness.database.Routine;
import SparkFitness.database.Set;
import com.google.common.collect.Lists;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

import org.testng.Assert;
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

    @Test
    public void test_getAllRoutines_happyPath() {
        Mockito.when(fitnessDao.getRoutinesByUserId(0)).thenReturn(Lists.newArrayList(any(Routine.class)));
        Mockito.when(fitnessDao.getSetsByRoutineId(0)).thenReturn(Lists.newArrayList(any(Set.class)));
        Assert.assertNotNull(workoutBoImpl.getAllRoutines(1));
    }

    @Test
    public void test_getAllRoutines_nullRoutineList() {
        Mockito.when(fitnessDao.getRoutinesByUserId(0)).thenReturn(null);
        Mockito.when(fitnessDao.getSetsByRoutineId(0)).thenReturn(Lists.newArrayList(any(Set.class)));
        Assert.assertNotNull(workoutBoImpl.getAllRoutines(1));
    }

    @Test
    public void test_getAllRoutines_nullSetList() {
        Mockito.when(fitnessDao.getRoutinesByUserId(0)).thenReturn(Lists.newArrayList(any(Routine.class)));
        Mockito.when(fitnessDao.getSetsByRoutineId(0)).thenReturn(null);
        Assert.assertNotNull(workoutBoImpl.getAllRoutines(1));
    }

    @Test
    public void test_getAllRoutines_nullRoutineListAndSetList() {
        Mockito.when(fitnessDao.getRoutinesByUserId(0)).thenReturn(Lists.newArrayList(any(Routine.class)));
        Mockito.when(fitnessDao.getSetsByRoutineId(0)).thenReturn(null);
        Assert.assertNotNull(workoutBoImpl.getAllRoutines(1));
    }

    @Test
    public void test_getLastRoutine_happyPath() {
        Mockito.when(fitnessDao.getLastRoutineByUserId(0)).thenReturn(any(Routine.class));
        Mockito.when(fitnessDao.getSetsByRoutineId(0)).thenReturn(Lists.newArrayList(any(Set.class)));
        Assert.assertNotNull(workoutBoImpl.getLastRoutine(1));
    }

    @Test(enabled = false)
    public void test_saveRoutine_happyPath() {
        Routine routine = new Routine();
        routine.setRoutineId(1);
        routine.setUserId(1);
        routine.setName("test");
        routine.setType("chest");
        routine.setDate(new Date());

        Set set = new Set();
        routine.setSetList(Lists.newArrayList(set));
        Mockito.when(fitnessDao.insertRoutine(any(Routine.class))).thenReturn(anyInt());
        //Mockito.when(fitnessDao.insertSet(any(Set.class))).thenReturn();
        workoutBoImpl.saveRoutine(routine);
    }

    @Test(enabled = false)
    public void test_deleteRoutine() {
        workoutBoImpl.deleteRoutine(1);
        verify(fitnessDao).deleteRoutine(1);
    }
}