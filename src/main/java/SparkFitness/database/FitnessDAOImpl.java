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

    @Override
    public List<Routine> getAllRoutines(int userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("User ID cannot be less than 1.");
        }

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
}
