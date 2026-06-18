/**
 * @author: Chris Jimenez
 *
 * @purpose: The Task represents a single to-do item.
 * Stores the task name, point value, and completion status.
 */
package edu.utsa.cs3443.marvels_domodomo;
import java.util.UUID;//unique ID -> prevent name collision


public class Task {

    // Variables
    private final String id;
    private String taskName;
    private int points;
    private boolean taskIsCompleted;

    // Constructor
    public Task(String taskName, int points, boolean taskIsCompleted) {
        this.id = UUID.randomUUID().toString(); // auto-generate on creation = creates a unique ID
        this.taskName = taskName;
        this.points = points;
        this.taskIsCompleted = taskIsCompleted;
    }

    // Getters & Setters
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isTaskIsCompleted() {
        return taskIsCompleted;
    }
    public void setTaskIsCompleted(boolean taskIsCompleted) {
        this.taskIsCompleted = taskIsCompleted;
    }

    public String getId() {return id;}


    /**
     * Format foes like "taskName,points,true/false"
     * e.g.  "Finish To-Do portion in Figma,1,false"
     * Used for saving to tasks.txt
     */
    @Override
    public String toString() {
        return taskName + "," + points + "," + taskIsCompleted;
    }

}