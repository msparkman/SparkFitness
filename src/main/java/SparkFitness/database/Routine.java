package SparkFitness.database;

import java.util.Date;
import java.util.List;

/**
 * A pojo to hold the row information from the routines table in the database.
 *
 * @author Matt Sparkman
 */
public class Routine {
    private int routineId;
    private int userId;
    private Date date;
    private String type;
    private String name;
    private List<Set> setList;

    protected Routine() {}

    public int getRoutineId() {
        return routineId;
    }

    public void setRoutineId(int routineId) {
        this.routineId = routineId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Set> getSetList() {
        return setList;
    }

    public void setSetList(List<Set> setList) {
        this.setList = setList;
    }

    @Override
    public String toString() {
        return "Routine{" +
                "routineId=" + routineId +
                ", userId=" + userId +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", setList=" + setList +
                '}';
    }
}
