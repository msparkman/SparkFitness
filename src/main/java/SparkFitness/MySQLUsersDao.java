package SparkFitness;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A class for accessing the user database information.
 *
 * @author Matt Sparkman
 */
@Repository
public class MySQLUsersDao implements UsersDao {
    @Override
    public int getUserId() throws Exception {
        String url = "jdbc:mysql://localhost:3306/sparkfitness";
        String username = "sparkfitness";
        String password = "gainstrain";

        int userId = 0;

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT user_id FROM users");

        try {
            if (resultSet != null && resultSet.first()) {
                userId = resultSet.getInt(1);
            }
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return userId;
    }
}