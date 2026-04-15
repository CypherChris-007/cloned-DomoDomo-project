/**
 * @author: Chris Jimenez
 *
 * @purpose: The Task represents a single to-do item.
 * It stores the completion status (taskIsCompleted) and has a constructor + toString().
 *
 */
package edu.utsa.cs3443.marvels_domodomo;

public class Task {
//Variables
private boolean taskIsCompleted;
//Constructor
    public Task(boolean pTaskIsCompleted) {
        this.taskIsCompleted = pTaskIsCompleted;
    }
    //toString | taskIsCompleted -> True or False
    @Override
    public String toString() {
        if (taskIsCompleted) {
            return "True";
        } else {
            return "False";
        }

    }
}
