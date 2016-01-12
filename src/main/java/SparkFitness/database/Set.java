package SparkFitness.database;

/**
 * A pojo to hold the row information from the sets table in the database.
 *
 * @author Matt Sparkman
 */
public class Set {
    private int setId;
    private int routineId;
    private int numberOfReps;
    private int weight;
    private String comment;

    public Set() {}

    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public int getRoutineId() {
        return routineId;
    }

    public void setRoutineId(int routineId) {
        this.routineId = routineId;
    }

    public int getNumberOfReps() {
        return numberOfReps;
    }

    public void setNumberOfReps(int numberOfReps) {
        this.numberOfReps = numberOfReps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Set{" +
                "setId=" + setId +
                ", routineId=" + routineId +
                ", numberOfReps=" + numberOfReps +
                ", weight=" + weight +
                ", comment='" + comment + '\'' +
                '}';
    }
}
