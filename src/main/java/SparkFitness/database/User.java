package SparkFitness.database;

import SparkFitness.database.Routine;

import java.util.Date;
import java.util.List;

/**
 * A pojo to hold the row information from the users table in the database.
 *
 * @author Matt Sparkman
 */
public class User {

    private int userId;
    private String username;
    private String password;
    private Date dateCreated;
    private List<Routine> routineList;

    protected User() {}

    public User(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Routine> getRoutineList() {
        return routineList;
    }

    public void setRoutineList(List<Routine> routineList) {
        this.routineList = routineList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                ", routineList=" + routineList +
                '}';
    }
}
