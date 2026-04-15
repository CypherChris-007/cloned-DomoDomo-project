/**
 * @author: Chris Jimenez
 *
 * @purpose: The TaskManager manages an ArrayList<Task>.  This handles adding, editing and removing tasks.
 */
package edu.utsa.cs3443.marvels_domodomo;

import java.util.ArrayList;

public class TaskManager {
//Variables
    private ArrayList <Task> task;
    //constructor
    public TaskManager(ArrayList<Task> task) {
        this.task = task;
    }
    //setters & getters
    public void setTask(ArrayList<Task> task) {
        this.task = task;
    }
    public ArrayList<Task> getTask() {
        return task;
    }
    //methods
    public void addTask(Task pTask){
        task.add(pTask);
    }
    public void removeTask(Task pTask){
        task.remove(pTask);
    }
    public void editTask(Task pTask){
    //locate index -> to edit
    int index = task.indexOf(pTask);
    //replace task with new or updated task
    task.add(index, pTask);
    }
//toString
    @Override
    public String toString() {
        return "TaskManager{" +
                "task=" + task +
                '}';
    }
}
