package SparkFitness.database;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

/**
 * @author Matt Sparkman
 */
@Repository
public class FitnessDAOImpl implements FitnessDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<Routine> ROUTINE_ROW_MAPPER = (resultSet, rowNumber) -> {
        Routine routine = new Routine();
        routine.setRoutineId(resultSet.getInt("routine_id"));
        routine.setUserId(resultSet.getInt("user_id"));
        routine.setDate(resultSet.getDate("date"));
        routine.setType(resultSet.getString("type"));
        routine.setName(resultSet.getString("name"));
        return routine;
    };

    private static final RowMapper<Set> SET_ROW_MAPPER = (resultSet, rowNumber) -> {
        Set set = new Set();
        set.setSetId(resultSet.getInt("set_id"));
        set.setRoutineId(resultSet.getInt("routine_id"));
        set.setNumberOfReps(resultSet.getInt("num_reps"));
        set.setWeight(resultSet.getInt("weight"));
        set.setComment(resultSet.getString("comment"));
        return set;
    };

    @Override
    public List<Routine> getRoutinesByUserId(int userId) {
        List<Routine> routineList = jdbcTemplate.query("SELECT " +
                        "* " +
                        "FROM " +
                        "routines " +
                        "WHERE " +
                        "user_id = ?",
                new Object[] { userId },
                new int[] { Types.INTEGER },
                ROUTINE_ROW_MAPPER);
        if (routineList == null) {
            return Lists.newArrayList();
        }

        return routineList;
    }

    @Override
    public Routine getLastRoutineByUserId(int userId) {
        Routine routine = jdbcTemplate.queryForObject("SELECT " +
                        "* " +
                        "FROM " +
                        "routines " +
                        "WHERE " +
                        "user_id = ? " +
                        "ORDER BY " +
                        "date DESC " +
                        "LIMIT 1",
                new Object[]{userId},
                ROUTINE_ROW_MAPPER);
        if (routine == null) {
            return new Routine();
        }

        return routine;
    }

    @Override
    public void insertRoutine(Routine routine) {
        jdbcTemplate.update("INSERT INTO " +
                "routines " +
                "(user_id," +
                "date," +
                "type," +
                "name) " +
                "VALUES " +
                "(?, ?, ?, ?)",
                new Object[] { routine.getUserId(), routine.getDate(), routine.getType(), routine.getName() },
                new int[] { Types.INTEGER, Types.DATE, Types.VARCHAR, Types.VARCHAR });
    }

    @Override
    public void deleteRoutine(int routineId) {
        jdbcTemplate.update("DELETE FROM " +
                        "routines " +
                        "WHERE " +
                        "routine_id = ? ",
                new Object[] { routineId },
                new int[] { Types.INTEGER });
    }

    @Override
    public List<Set> getSetsByRoutineId(int routineId) {
        List<Set> setList = jdbcTemplate.query("SELECT " +
                        "* " +
                        "FROM " +
                        "sets " +
                        "WHERE " +
                        "routine_id = ?",
                new Object[] { routineId },
                new int[] { Types.INTEGER },
                SET_ROW_MAPPER);
        if (setList == null) {
            return Lists.newArrayList();
        }

        return setList;
    }

    @Override
    public void insertSet(Set set) {
        jdbcTemplate.update("INSERT INTO " +
                        "sets " +
                        "(routine_id," +
                        "num_reps," +
                        "weight," +
                        "comment) " +
                        "VALUES " +
                        "(?, ?, ?, ?)",
                new Object[] { set.getRoutineId(), set.getNumberOfReps(), set.getWeight(), set.getComment() },
                new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR });
    }

    @Override
    public void deleteSetsByRoutineId(int routineId) {
        jdbcTemplate.update("DELETE FROM " +
                        "sets " +
                        "WHERE " +
                        "routine_id = ? ",
                new Object[] { routineId },
                new int[] { Types.INTEGER });
    }
}
