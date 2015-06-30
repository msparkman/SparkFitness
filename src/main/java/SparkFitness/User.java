package SparkFitness;

/**
 * @author Matt Sparkman
 */
public class User {

    private int userId;
    private String username;
    private String password;
    private long dateCreated;

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

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return String.format(
                "User[userId=%d, username='%s', password='%s', dateCreated=%d",
                userId, username, password, dateCreated);
    }
}
