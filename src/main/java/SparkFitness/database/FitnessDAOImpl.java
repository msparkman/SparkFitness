package SparkFitness.database;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
    public int insertRoutine(Routine routine) {
        final String INSERT_SQL = "INSERT INTO " +
                "routines " +
                "(user_id," +
                "date," +
                "type," +
                "name) " +
                "VALUES " +
                "(?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps =
                    connection.prepareStatement(INSERT_SQL, new String[] {"user_id", "date", "type", "name"});
            ps.setInt(1, routine.getUserId());
            ps.setDate(2, new Date(routine.getDate().getTime()));
            ps.setString(3, routine.getType());
            ps.setString(4, routine.getName());

            return ps;

        }, keyHolder);

        return keyHolder.getKey().intValue();
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
