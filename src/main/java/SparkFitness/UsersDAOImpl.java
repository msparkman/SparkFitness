package SparkFitness;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A class for accessing the user database information.
 *
 * @author Matt Sparkman
 */
@Repository
public class UsersDAOImpl implements UsersDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<User> USER_ROW_MAPPER = (resultSet, rowNumber) -> {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        return user;
    };

    @Override
    public List<Integer> getUserIds() {
        List<User> userList = jdbcTemplate.query("SELECT user_id FROM users", USER_ROW_MAPPER);
        if (userList == null) {
            return Lists.newArrayList();
        }

        return userList.stream().map(User::getUserId).collect(Collectors.toList());
    }
}